package com.aurum.services

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["com.aurum.services"])
class SeleneApplication

fun main(args: Array<String>) {
	SpringApplication.run(SeleneApplication::class.java, *args)
}
