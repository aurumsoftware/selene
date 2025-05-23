package com.aurum.services.microservice.controller

import com.aurum.services.microservice.application.security.ports.ApplicationPropertiesInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@RestController
class MicroserviceTemplateAPI @Autowired constructor(
    private val applicationPropertiesInterface: ApplicationPropertiesInterface
) {

    @RequestMapping("/")
    fun redirect(attributes: RedirectAttributes) =
        RedirectView("${applicationPropertiesInterface.getContextPath() ?: ""}".plus("/swagger-ui/index.html"))
}