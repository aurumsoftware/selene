package com.aurum.services.microservice.controller.example

import com.aurum.services.microservice.application.example.port.ExampleInterfaceCallbackStringQueue
import com.aurum.services.microservice.application.example.port.ExampleInterfaceHttp
import com.aurum.services.microservice.controller.example.request.ExampleRequest
import com.aurum.services.microservice.controller.example.response.ExampleResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Tag(name = "ExampleAPi")
@RestController
@RequestMapping("api/example")
class ExampleAPI @Autowired constructor(
    private val exampleInterfaceHttp: ExampleInterfaceHttp,
    private val exampleInterfaceCallbackStringQueue: ExampleInterfaceCallbackStringQueue
) {

    @GetMapping("/")
    @Operation(summary ="Retorna um Hello World")
    fun helloWorld(): ExampleResponse
            = ExampleResponse(exampleInterfaceHttp.getHelloWorldFromHttpModule())

    @PostMapping("/")
    @Operation(summary = "Retorna um Callback do que foi enviado")
    fun listByClippingCodes(@RequestBody example: ExampleRequest): ExampleResponse {
        return ExampleResponse(exampleInterfaceCallbackStringQueue.getCallbackFromHttpModule(example.getExampleDomain()))
    }
}
