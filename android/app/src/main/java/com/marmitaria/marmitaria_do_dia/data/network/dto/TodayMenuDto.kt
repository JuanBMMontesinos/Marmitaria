package com.marmitaria.marmitaria_do_dia.data.network.dto

import com.google.gson.annotations.SerializedName

data class TodayMenuDto(
    @SerializedName("is_open", alternate = ["isOpen"]) val isOpen: Boolean? = true,
    @SerializedName("day_name", alternate = ["dayName"]) val dayName: String? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("dishes", alternate = ["options"]) val options: List<MealOptionDto>? = emptyList(),
    @SerializedName("beverages", alternate = ["drinks"]) val drinks: List<DrinkDto>? = emptyList(),
    @SerializedName("addons") val addons: List<AddonDto>? = emptyList()
)

data class MealOptionDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("option_label", alternate = ["num"]) val num: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("ingredients", alternate = ["accompaniments"]) val accompaniments: String? = null,
    @SerializedName("price") val price: Double? = null
)

data class DrinkDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("ingredients", alternate = ["desc"]) val desc: String? = null,
    @SerializedName("price") val price: Double? = null
)

data class AddonDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("price") val price: Double? = null
)
