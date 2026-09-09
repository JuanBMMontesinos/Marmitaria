package com.marmitaria.marmitaria_do_dia.data.network.dto

import com.google.gson.annotations.SerializedName

data class DayMenuDto(
    @SerializedName("day_name", alternate = ["dayName"]) val dayName: String? = null,
    @SerializedName("day_of_week", alternate = ["dayOfWeek"]) val dayOfWeek: String? = null,
    @SerializedName("dishes", alternate = ["options"]) val options: List<MealOptionDto>? = emptyList()
)

data class WeeklyMenuDto(
    @SerializedName("weekly_schedule", alternate = ["weeklySchedule", "days"]) val weeklySchedule: List<DayMenuDto>? = emptyList(),
    @SerializedName("weeklyMenu") val weeklyMenu: Map<String, List<MealOptionDto>>? = null
)
