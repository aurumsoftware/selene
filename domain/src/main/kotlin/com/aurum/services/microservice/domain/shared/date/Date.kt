package com.aurum.services.microservice.domain.shared.date

import java.time.LocalDate

class Date(private val date: LocalDate) {

    val year: Int
        get() = date.year
    val month: Int
        get() = date.monthValue

    companion object {
        val TODAY = Date(LocalDate.now())
    }
}
