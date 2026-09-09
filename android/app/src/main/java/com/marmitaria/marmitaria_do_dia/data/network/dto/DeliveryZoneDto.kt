package com.marmitaria.marmitaria_do_dia.data.network.dto

import com.google.gson.annotations.SerializedName

data class DeliveryZoneResponseDto(
    @SerializedName("value") val value: List<DeliveryZoneDto>? = null
)

data class DeliveryZoneDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("neighborhood") val neighborhood: String? = null,
    @SerializedName("delivery_fee", alternate = ["deliveryFee"]) val deliveryFee: Double? = null,
    @SerializedName("estimated_time_min") val estimatedTimeMin: Int? = null
)
