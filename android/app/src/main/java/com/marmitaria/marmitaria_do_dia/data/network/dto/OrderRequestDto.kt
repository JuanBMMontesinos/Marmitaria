package com.marmitaria.marmitaria_do_dia.data.network.dto

import com.google.gson.annotations.SerializedName

data class OrderRequestDto(
    @SerializedName("customer") val customer: CustomerDto,
    @SerializedName("address") val address: AddressDto? = null,
    @SerializedName("delivery_type", alternate = ["deliveryType"]) val deliveryType: String,
    @SerializedName("payment_method", alternate = ["paymentMethod"]) val paymentMethod: String,
    @SerializedName("cash_change_for", alternate = ["cashChangeFor", "changeFor"]) val cashChangeFor: Double? = null,
    @SerializedName("items") val items: List<OrderItemRequestDto> = emptyList(),
    // Fallback fields for alternative API schemas
    @SerializedName("clientName") val clientName: String? = null,
    @SerializedName("clientPhone") val clientPhone: String? = null,
    @SerializedName("totalAmount") val totalAmount: Double? = null
)

data class CustomerDto(
    @SerializedName("full_name", alternate = ["fullName", "name", "clientName"]) val fullName: String,
    @SerializedName("phone", alternate = ["clientPhone"]) val phone: String
)

data class AddressDto(
    @SerializedName("street") val street: String? = null,
    @SerializedName("number") val number: String? = null,
    @SerializedName("neighborhood") val neighborhood: String? = null,
    @SerializedName("zip_code", alternate = ["zipCode", "cep"]) val zipCode: String? = null,
    @SerializedName("complement") val complement: String? = null,
    @SerializedName("reference") val reference: String? = null
)

data class OrderItemRequestDto(
    @SerializedName("menu_item_id", alternate = ["menuItemId", "id"]) val menuItemId: String,
    @SerializedName("quantity", alternate = ["qty"]) val quantity: Int = 1,
    @SerializedName("notes", alternate = ["preferences"]) val notes: String? = null,
    @SerializedName("selected_addons", alternate = ["adicionais"]) val selectedAddons: List<String>? = emptyList(),
    @SerializedName("name") val name: String? = null,
    @SerializedName("unitPrice") val unitPrice: Double? = null
)
