package com.aurum.services.microservice.http.endpoints

import com.aurum.services.microservice.http.call.ClippingAccountDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExampleProvider {
    @GET("/api/v2/clipping-search/accounts-to-update/{provider}")
    fun getOffices(@Path("provider") provider: String): Call<List<ClippingAccountDTO>>
}