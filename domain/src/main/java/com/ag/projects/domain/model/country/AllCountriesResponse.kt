package com.ag.projects.domain.model.country

data class AllCountriesResponse(
    val data: List<Country>,
    val message: String,
    val status: String
)