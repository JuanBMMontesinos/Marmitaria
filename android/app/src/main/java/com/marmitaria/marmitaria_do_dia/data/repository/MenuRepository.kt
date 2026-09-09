package com.marmitaria.marmitaria_do_dia.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marmitaria.marmitaria_do_dia.data.model.Addon
import com.marmitaria.marmitaria_do_dia.data.model.DayMenu
import com.marmitaria.marmitaria_do_dia.data.model.Drink
import com.marmitaria.marmitaria_do_dia.data.model.MealOption
import com.marmitaria.marmitaria_do_dia.data.network.MarmitariaApiService
import com.marmitaria.marmitaria_do_dia.data.network.RetrofitClient
import com.marmitaria.marmitaria_do_dia.data.network.dto.AddonDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.DeliveryZoneDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.DeliveryZoneResponseDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.DrinkDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.MealOptionDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.OrderRequestDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.OrderResponseDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.SettingsDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.TodayMenuDto

object MenuRepository {

    const val WHATSAPP_PHONE = "5511970599173"
    const val PIX_KEY = "11970599173"
    const val RESTAURANT_ADDRESS = "Rua das Marmitas, 500 - Centro"
    const val TAKEOUT_OPEN_TIME = "11:30h"

    val weekdays = listOf(
        "Segunda-feira",
        "Terça-feira",
        "Quarta-feira",
        "Quinta-feira",
        "Sexta-feira",
        "Sábado"
    )

    val availableAddons = listOf(
        Addon(id = "add_egg", name = "Ovo Frito extra", price = 3.00),
        Addon(id = "add_potato", name = "Batata Palha extra", price = 2.00),
        Addon(id = "add_farofa", name = "Farofa extra", price = 2.00),
        Addon(id = "add_soda", name = "Refrigerante Lata 350ml", price = 6.00),
        Addon(id = "add_mini_soda", name = "Mini Refrigerante 200ml", price = 3.00)
    )

    val drinks = listOf(
        Drink(
            id = "drink_1",
            name = "Refrigerante Lata 350ml",
            desc = "Coca-Cola, Guaraná ou Fanta",
            price = 6.00
        ),
        Drink(
            id = "drink_2",
            name = "Mini Refrigerante 200ml",
            desc = "Sabor Coca-Cola ou Guaraná",
            price = 3.00
        )
    )

    val weeklyMenu: Map<String, DayMenu> = mapOf(
        "Segunda-feira" to DayMenu(
            dayName = "Segunda-feira",
            options = listOf(
                MealOption("mon_1", "1ª Opção", "Strogonoff de Carne", "Arroz e batata palha", 27.00),
                MealOption("mon_2", "2ª Opção", "Berinjela à Parmegiana", "Arroz e batata cozida", 27.00),
                MealOption("mon_3", "3ª Opção", "Bisteca Grelhada", "Arroz, feijão e farofa", 27.00),
                MealOption("mon_4", "4ª Opção", "Filé de Frango Grelhado", "Arroz, feijão e legumes", 27.00),
                MealOption("mon_5", "5ª Opção", "Linguiça Acebolada", "Arroz, feijão e farofa", 27.00),
                MealOption("mon_6", "6ª Opção", "Omelete de Presunto e Queijo", "Arroz, feijão e legumes", 27.00)
            )
        ),
        "Terça-feira" to DayMenu(
            dayName = "Terça-feira",
            options = listOf(
                MealOption("tue_1", "1ª Opção", "Carne de Panela", "Arroz, feijão e purê", 27.00),
                MealOption("tue_2", "2ª Opção", "Pernil Assado", "Arroz, feijão, farofa e vinagrete", 27.00),
                MealOption("tue_3", "3ª Opção", "Strogonoff de Frango", "Arroz e batata palha", 27.00),
                MealOption("tue_4", "4ª Opção", "Filé de Frango Grelhado", "Arroz, feijão e purê", 27.00),
                MealOption("tue_5", "5ª Opção", "Linguiça Acebolada", "Arroz, feijão e farofa", 27.00),
                MealOption("tue_6", "6ª Opção", "Omelete de Presunto e Queijo", "Arroz, feijão e purê", 27.00)
            )
        ),
        "Quarta-feira" to DayMenu(
            dayName = "Quarta-feira",
            options = listOf(
                MealOption("wed_1", "1ª Opção", "Feijoada Light", "Arroz, couve, farofa e molho de pimenta", 40.00),
                MealOption("wed_2", "2ª Opção", "Coxa e Sobrecoxa Assada", "Arroz, feijão e macarrão", 27.00),
                MealOption("wed_3", "3ª Opção", "Bife a Cavalo", "Arroz, feijão e farofa", 27.00),
                MealOption("wed_4", "4ª Opção", "Filé de Frango Grelhado", "Arroz, feijão e legumes", 27.00),
                MealOption("wed_5", "5ª Opção", "Linguiça Acebolada", "Arroz, feijão e farofa", 27.00),
                MealOption("wed_6", "6ª Opção", "Omelete de Presunto e Queijo", "Arroz, feijão e purê", 27.00)
            )
        ),
        "Quinta-feira" to DayMenu(
            dayName = "Quinta-feira",
            options = listOf(
                MealOption("thu_1", "1ª Opção", "Filé de Frango à Parmegiana", "Arroz, feijão e batata cozida", 27.00),
                MealOption("thu_2", "2ª Opção", "Panqueca de Carne", "Arroz à grega e batata cozida", 27.00),
                MealOption("thu_3", "3ª Opção", "Filé de Frango à Milanesa", "Arroz, feijão e batata cozida", 27.00),
                MealOption("thu_4", "4ª Opção", "Filé de Frango Grelhado", "Arroz, feijão e legumes", 27.00),
                MealOption("thu_5", "5ª Opção", "Linguiça Acebolada", "Arroz, feijão e farofa", 27.00),
                MealOption("thu_6", "6ª Opção", "Omelete de Presunto e Queijo", "Arroz, feijão e legumes", 27.00)
            )
        ),
        "Sexta-feira" to DayMenu(
            dayName = "Sexta-feira",
            options = listOf(
                MealOption("fri_1", "1ª Opção", "Bife à Parmegiana", "Arroz, feijão e purê", 27.00),
                MealOption("fri_2", "2ª Opção", "Picadinho de Carne", "Batata, cenoura, arroz, feijão e farofa", 27.00),
                MealOption("fri_3", "3ª Opção", "Posta de Cação ao Molho", "Arroz, feijão e purê", 27.00),
                MealOption("fri_4", "4ª Opção", "Filé de Frango Grelhado", "Arroz, feijão e purê", 27.00),
                MealOption("fri_5", "5ª Opção", "Linguiça Acebolada", "Arroz, feijão e farofa", 27.00),
                MealOption("fri_6", "6ª Opção", "Omelete de Presunto e Queijo", "Arroz, feijão e purê", 27.00)
            )
        ),
        "Sábado" to DayMenu(
            dayName = "Sábado",
            options = listOf(
                MealOption("sat_1", "1ª Opção", "Feijoada Light", "Arroz, couve e molho de pimenta", 40.00),
                MealOption("sat_2", "2ª Opção", "Strogonoff de Frango", "Arroz, feijão e batata palha", 27.00),
                MealOption("sat_3", "3ª Opção", "Bife Grelhado", "Arroz, feijão e legumes", 27.00),
                MealOption("sat_4", "4ª Opção", "Filé de Frango Grelhado", "Arroz, feijão e legumes", 27.00),
                MealOption("sat_5", "5ª Opção", "Linguiça Acebolada", "Arroz, feijão e farofa", 27.00),
                MealOption("sat_6", "6ª Opção", "Omelete de Presunto e Queijo", "Arroz, feijão e legumes", 27.00)
            )
        )
    )

    // Conversões de DTO para Modelo de Domínio
    fun MealOptionDto.toDomain(): MealOption = MealOption(
        id = id ?: "",
        num = num ?: "",
        name = name ?: "",
        accompaniments = accompaniments ?: "",
        price = price ?: 27.00
    )

    fun DrinkDto.toDomain(): Drink = Drink(
        id = id ?: "",
        name = name ?: "",
        desc = desc ?: "",
        price = price ?: 0.0
    )

    fun AddonDto.toDomain(): Addon = Addon(
        id = id ?: "",
        name = name ?: "",
        price = price ?: 0.0
    )

    // Chamadas da API com tratamento via runCatching / Result
    suspend fun fetchTodayMenu(apiService: MarmitariaApiService = RetrofitClient.apiService): Result<TodayMenuDto> = runCatching {
        apiService.getTodayMenu()
    }

    suspend fun fetchWeeklyMenu(apiService: MarmitariaApiService = RetrofitClient.apiService): Result<Map<String, DayMenu>> = runCatching {
        val dto = apiService.getWeeklyMenu()
        val result = mutableMapOf<String, DayMenu>()

        dto.weeklySchedule?.forEach { dayDto ->
            val dayName = dayDto.dayName ?: return@forEach
            result[dayName] = DayMenu(
                dayName = dayName,
                options = dayDto.options?.map { it.toDomain() } ?: emptyList()
            )
        }

        dto.weeklyMenu?.forEach { (day, options) ->
            result[day] = DayMenu(
                dayName = day,
                options = options.map { it.toDomain() }
            )
        }

        if (result.isEmpty()) {
            weeklyMenu
        } else {
            result
        }
    }

    suspend fun fetchSettings(apiService: MarmitariaApiService = RetrofitClient.apiService): Result<SettingsDto> = runCatching {
        apiService.getSettings()
    }

    suspend fun fetchDeliveryZones(apiService: MarmitariaApiService = RetrofitClient.apiService): Result<List<DeliveryZoneDto>> = runCatching {
        val element = apiService.getDeliveryZones()
        val gson = Gson()
        if (element.isJsonObject) {
            val responseObj = gson.fromJson(element, DeliveryZoneResponseDto::class.java)
            responseObj.value ?: emptyList()
        } else if (element.isJsonArray) {
            val listType = object : TypeToken<List<DeliveryZoneDto>>() {}.type
            gson.fromJson<List<DeliveryZoneDto>>(element, listType) ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun createOrder(
        orderRequest: OrderRequestDto,
        apiService: MarmitariaApiService = RetrofitClient.apiService
    ): Result<OrderResponseDto> = runCatching {
        apiService.createOrder(orderRequest)
    }
}
