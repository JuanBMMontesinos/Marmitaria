package com.marmitaria.marmitaria_do_dia.data.network.dto

import com.google.gson.annotations.SerializedName

data class SettingsDto(
    @SerializedName("phone_whatsapp", alternate = ["whatsappPhone"]) val whatsappPhone: String? = null,
    @SerializedName("pix_key", alternate = ["pixKey"]) val pixKey: String? = null,
    @SerializedName("address_text", alternate = ["restaurantAddress"]) val restaurantAddress: String? = null,
    @SerializedName("takeout_open_time", alternate = ["takeoutOpenTime"]) val takeoutOpenTime: String? = null,
    @SerializedName("is_open", alternate = ["currently_open"]) val isOpen: Boolean? = true
)
