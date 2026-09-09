package com.marmitaria.marmitaria_do_dia.data.network

import com.google.gson.JsonElement
import com.marmitaria.marmitaria_do_dia.data.network.dto.OrderRequestDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.OrderResponseDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.SettingsDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.TodayMenuDto
import com.marmitaria.marmitaria_do_dia.data.network.dto.WeeklyMenuDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MarmitariaApiService {

    @GET("menu/today")
    suspend fun getTodayMenu(): TodayMenuDto

    @GET("menu/weekly")
    suspend fun getWeeklyMenu(): WeeklyMenuDto

    @GET("settings")
    suspend fun getSettings(): SettingsDto

    @GET("delivery-zones")
    suspend fun getDeliveryZones(): JsonElement

    @POST("orders")
    suspend fun createOrder(@Body orderRequest: OrderRequestDto): OrderResponseDto
}
