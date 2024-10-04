package com.ag.projects.domain.model.qa.about

data class AboutResponse(
    val data: List<AboutItems>,
    val message: String,
    val status: String
)