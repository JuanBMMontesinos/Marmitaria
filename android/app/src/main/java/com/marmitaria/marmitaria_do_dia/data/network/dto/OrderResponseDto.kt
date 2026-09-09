package com.marmitaria.marmitaria_do_dia.data.network.dto

import com.google.gson.annotations.SerializedName

data class OrderResponseDto(
    @SerializedName("order_number", alternate = ["orderNumber", "orderId", "id"]) val orderNumber: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("pix_key", alternate = ["pixKey"]) val pixKey: String? = null,
    @SerializedName("qr_code", alternate = ["qrCode"]) val qrCode: String? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("total_amount", alternate = ["totalAmount"]) val totalAmount: Double? = null
)
