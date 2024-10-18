package com.ag.projects.domain.model.country

data class Country(
    val id: Int,
    val image: String,
    val name: Any,
    val nationality: Any,
    val phone_code: String,
    val phone_number_limit: Int
)