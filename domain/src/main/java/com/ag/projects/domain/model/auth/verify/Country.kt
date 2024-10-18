package com.ag.projects.domain.model.auth.verify

data class Country(
    val id: Int,
    val image: String,
    val name: String,
    val nationality: String,
    val phone_code: String,
    val phone_number_limit: Int
)