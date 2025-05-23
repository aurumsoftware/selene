package com.aurum.services.microservice.http.call

import com.aurum.services.microservice.http.RetrofitBuilder
import com.aurum.services.microservice.http.endpoints.ExampleProvider
import org.springframework.beans.factory.annotation.Autowired

class HttpCallExample @Autowired constructor(
    private val builder: RetrofitBuilder
) {
    fun execute(): List<ClippingAccountDTO>? {
        return builder.build<ExampleProvider>(RetrofitBuilder.BASE_URL)
            .getOffices("AASP")
            .execute()
            .body()
    }
}