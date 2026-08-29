package com.marmitaria.marmitaria_do_dia.data.model

import java.util.UUID

data class MealOption(
    val id: String,
    val num: String,
    val name: String,
    val accompaniments: String,
    val price: Double = 27.00
)

data class DayMenu(
    val dayName: String,
    val options: List<MealOption>
)

data class Drink(
    val id: String,
    val name: String,
    val desc: String,
    val price: Double
)

data class Addon(
    val id: String,
    val name: String,
    val price: Double
)

data class CartItem(
    val cartId: String = UUID.randomUUID().toString(),
    val id: String,
    val name: String,
    val num: String = "",
    val qty: Int = 1,
    val preferences: String = "",
    val adicionais: List<Addon> = emptyList(),
    val unitPrice: Double
) {
    val totalPrice: Double
        get() = unitPrice * qty
}

enum class DeliveryType {
    DELIVERY,
    TAKEOUT
}

enum class PaymentMethod(val label: String, val description: String) {
    PIX("PIX", "Aprovação imediata, chaves geradas online"),
    CREDIT_CARD("Cartão de Crédito Online", "Visa, Mastercard, Elo, Hipercard"),
    CASH_ON_DELIVERY("Dinheiro na Entrega", "Pague na entrega com dinheiro físico"),
    CARD_ON_DELIVERY("Maquininha na Entrega", "Crédito, Débito ou Ticket Refeição")
}

data class OrderDetails(
    val clientName: String = "",
    val clientPhone: String = "",
    val deliveryType: DeliveryType = DeliveryType.DELIVERY,
    val cep: String = "",
    val neighborhood: String = "",
    val street: String = "",
    val number: String = "",
    val complement: String = "",
    val reference: String = "",
    val paymentMethod: PaymentMethod = PaymentMethod.PIX,
    val needChange: Boolean = false,
    val changeFor: Double = 0.0,
    val cardNumber: String = "",
    val cardOwner: String = "",
    val cardExpiry: String = "",
    val cardCvv: String = ""
)
