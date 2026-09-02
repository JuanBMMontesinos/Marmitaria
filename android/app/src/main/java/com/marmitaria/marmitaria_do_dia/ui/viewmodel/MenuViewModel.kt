package com.marmitaria.marmitaria_do_dia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmitaria.marmitaria_do_dia.data.model.Addon
import com.marmitaria.marmitaria_do_dia.data.model.CartItem
import com.marmitaria.marmitaria_do_dia.data.model.DayMenu
import com.marmitaria.marmitaria_do_dia.data.model.DeliveryType
import com.marmitaria.marmitaria_do_dia.data.model.Drink
import com.marmitaria.marmitaria_do_dia.data.model.MealOption
import com.marmitaria.marmitaria_do_dia.data.model.MenuMode
import com.marmitaria.marmitaria_do_dia.data.model.OrderDetails
import com.marmitaria.marmitaria_do_dia.data.model.PaymentMethod
import com.marmitaria.marmitaria_do_dia.data.repository.MenuRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class MenuUiState(
    val menuMode: MenuMode = MenuMode.TODAY_ONLY,
    val todayName: String = "Segunda-feira",
    val selectedDay: String = "Segunda-feira",
    val isSunday: Boolean = false,
    val showSundayWarning: Boolean = false,
    val cartItems: List<CartItem> = emptyList(),
    val isCustomizingOpen: Boolean = false,
    val customizingMeal: MealOption? = null,
    val customizingQty: Int = 1,
    val customizingPreferences: String = "",
    val customizingSelectedAddons: List<Addon> = emptyList(),
    val isCartOpen: Boolean = false,
    val isCheckoutOpen: Boolean = false,
    val isPixDialogOpen: Boolean = false,
    val activeNavTab: Int = 0, // 0: Menu, 1: Cart, 2: Tracking
    val orderDetails: OrderDetails = OrderDetails(),
    val trackingStep: Int = 0,
    val trackingStepTimes: List<String> = listOf("--:--", "--:--", "--:--", "--:--"),
    val toastMessage: String? = null
) {
    val displayedDay: String
        get() = if (menuMode == MenuMode.TODAY_ONLY) todayName else selectedDay

    val currentDayMenu: DayMenu?
        get() = MenuRepository.weeklyMenu[displayedDay]

    val isOrderingAllowedForSelectedDay: Boolean
        get() {
            if (isSunday) return false
            return if (menuMode == MenuMode.TODAY_ONLY) {
                true
            } else {
                selectedDay == todayName
            }
        }

    val subtotal: Double
        get() = cartItems.sumOf { it.totalPrice }

    val total: Double
        get() = subtotal // Frete grátis

    val totalCartCount: Int
        get() = cartItems.sumOf { it.qty }

    val customizingItemUnitPrice: Double
        get() {
            val base = customizingMeal?.price ?: 27.00
            val adds = customizingSelectedAddons.sumOf { it.price }
            return base + adds
        }

    val customizingItemTotalPrice: Double
        get() = customizingItemUnitPrice * customizingQty
}

class MenuViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()

    private var trackingJob: Job? = null
    private val timeFormat = SimpleDateFormat("HH:mm", Locale("pt", "BR"))

    init {
        detectCurrentDay()
    }

    private fun detectCurrentDay() {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        if (dayOfWeek == Calendar.SUNDAY) {
            _uiState.update {
                it.copy(
                    todayName = "Segunda-feira",
                    selectedDay = "Segunda-feira",
                    isSunday = true,
                    showSundayWarning = true
                )
            }
        } else {
            val dayName = when (dayOfWeek) {
                Calendar.MONDAY -> "Segunda-feira"
                Calendar.TUESDAY -> "Terça-feira"
                Calendar.WEDNESDAY -> "Quarta-feira"
                Calendar.THURSDAY -> "Quinta-feira"
                Calendar.FRIDAY -> "Sexta-feira"
                Calendar.SATURDAY -> "Sábado"
                else -> "Segunda-feira"
            }
            _uiState.update {
                it.copy(
                    todayName = dayName,
                    selectedDay = dayName,
                    isSunday = false,
                    showSundayWarning = false
                )
            }
        }
    }

    fun setMenuMode(mode: MenuMode) {
        _uiState.update {
            it.copy(
                menuMode = mode,
                showSundayWarning = false
            )
        }
    }

    fun selectDay(day: String) {
        _uiState.update {
            it.copy(
                selectedDay = day,
                showSundayWarning = false
            )
        }
        showToast("Mostrando cardápio de $day")
    }

    fun openCustomizeModal(meal: MealOption) {
        val state = _uiState.value
        if (!state.isOrderingAllowedForSelectedDay) {
            if (state.isSunday) {
                showToast("Estamos fechados hoje (Domingo). Não é possível realizar pedidos.")
            } else {
                showToast("O cardápio de ${state.selectedDay} está em Modo Consulta. Mude para 'Cardápio de Hoje' para fazer seu pedido.")
            }
            return
        }

        _uiState.update {
            it.copy(
                isCustomizingOpen = true,
                customizingMeal = meal,
                customizingQty = 1,
                customizingPreferences = "",
                customizingSelectedAddons = emptyList()
            )
        }
    }

    fun closeCustomizeModal() {
        _uiState.update { it.copy(isCustomizingOpen = false, customizingMeal = null) }
    }

    fun setCustomizingQty(delta: Int) {
        _uiState.update { state ->
            val newQty = (state.customizingQty + delta).coerceAtLeast(1)
            state.copy(customizingQty = newQty)
        }
    }

    fun updateCustomizingPreferences(pref: String) {
        _uiState.update { it.copy(customizingPreferences = pref) }
    }

    fun toggleAddon(addon: Addon) {
        _uiState.update { state ->
            val list = state.customizingSelectedAddons.toMutableList()
            if (list.any { it.id == addon.id }) {
                list.removeAll { it.id == addon.id }
            } else {
                list.add(addon)
            }
            state.copy(customizingSelectedAddons = list)
        }
    }

    fun confirmCustomize() {
        val state = _uiState.value
        val meal = state.customizingMeal ?: return

        val item = CartItem(
            id = meal.id,
            name = meal.name,
            num = meal.num,
            qty = state.customizingQty,
            preferences = state.customizingPreferences.trim(),
            adicionais = state.customizingSelectedAddons,
            unitPrice = state.customizingItemUnitPrice,
            dayName = state.displayedDay
        )

        _uiState.update {
            it.copy(
                cartItems = it.cartItems + item,
                isCustomizingOpen = false,
                customizingMeal = null
            )
        }
        showToast("Adicionado: ${item.qty}x ${item.name}")
    }

    fun addDrink(drink: Drink) {
        val state = _uiState.value
        if (state.isSunday) {
            showToast("Estamos fechados hoje (Domingo). Não é possível realizar pedidos.")
            return
        }

        val item = CartItem(
            id = drink.id,
            name = drink.name,
            qty = 1,
            unitPrice = drink.price,
            dayName = state.todayName
        )
        _uiState.update {
            it.copy(cartItems = it.cartItems + item)
        }
        showToast("Adicionado: ${drink.name}")
    }

    fun adjustCartQty(cartId: String, delta: Int) {
        _uiState.update { state ->
            val updated = state.cartItems.mapNotNull { item ->
                if (item.cartId == cartId) {
                    val newQty = item.qty + delta
                    if (newQty > 0) item.copy(qty = newQty) else null
                } else {
                    item
                }
            }
            state.copy(cartItems = updated)
        }
    }

    fun removeCartItem(cartId: String) {
        _uiState.update { state ->
            state.copy(cartItems = state.cartItems.filterNot { it.cartId == cartId })
        }
        showToast("Item removido do carrinho")
    }

    fun clearCart() {
        _uiState.update { it.copy(cartItems = emptyList()) }
        showToast("Carrinho esvaziado")
    }

    fun openCart() {
        _uiState.update { it.copy(isCartOpen = true) }
    }

    fun closeCart() {
        _uiState.update { it.copy(isCartOpen = false) }
    }

    fun openCheckout() {
        if (_uiState.value.cartItems.isEmpty()) {
            showToast("Adicione itens ao carrinho primeiro!")
            return
        }
        _uiState.update { it.copy(isCartOpen = false, isCheckoutOpen = true) }
    }

    fun closeCheckout() {
        _uiState.update { it.copy(isCheckoutOpen = false) }
    }

    fun updateOrderDetails(transform: (OrderDetails) -> OrderDetails) {
        _uiState.update { it.copy(orderDetails = transform(it.orderDetails)) }
    }

    fun setNavTab(tabIndex: Int) {
        _uiState.update { it.copy(activeNavTab = tabIndex) }
    }

    fun finalizeOrder() {
        val details = _uiState.value.orderDetails

        if (details.clientName.isBlank() || details.clientPhone.isBlank()) {
            showToast("Preencha Nome e Telefone!")
            return
        }

        if (details.deliveryType == DeliveryType.DELIVERY) {
            if (details.street.isBlank() || details.number.isBlank() || details.neighborhood.isBlank()) {
                showToast("Preencha os dados do endereço de entrega!")
                return
            }
        }

        if (details.paymentMethod == PaymentMethod.PIX) {
            _uiState.update { it.copy(isCheckoutOpen = false, isPixDialogOpen = true) }
        } else {
            confirmOrderCompletion()
        }
    }

    fun confirmPixPayment() {
        _uiState.update { it.copy(isPixDialogOpen = false) }
        confirmOrderCompletion()
    }

    fun closePixDialog() {
        _uiState.update { it.copy(isPixDialogOpen = false, isCheckoutOpen = true) }
    }

    private fun confirmOrderCompletion() {
        _uiState.update {
            it.copy(
                isCheckoutOpen = false,
                isCartOpen = false,
                isPixDialogOpen = false,
                activeNavTab = 2 // Tab Rastreador
            )
        }
        startTrackingSimulation()
    }

    private fun startTrackingSimulation() {
        trackingJob?.cancel()
        val currentTime = timeFormat.format(Date())

        _uiState.update {
            it.copy(
                trackingStep = 0,
                trackingStepTimes = listOf(currentTime, "--:--", "--:--", "--:--")
            )
        }

        trackingJob = viewModelScope.launch {
            for (step in 1..3) {
                delay(15000) // Simulação a cada 15 segundos
                val time = timeFormat.format(Date())
                _uiState.update { state ->
                    val times = state.trackingStepTimes.toMutableList()
                    if (step < times.size) times[step] = time
                    state.copy(trackingStep = step, trackingStepTimes = times)
                }
            }
        }
    }

    fun showToast(msg: String) {
        _uiState.update { it.copy(toastMessage = msg) }
    }

    fun clearToast() {
        _uiState.update { it.copy(toastMessage = null) }
    }
}
