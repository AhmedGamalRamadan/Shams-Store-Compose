package com.ag.projects.domain.model.qa.faq

data class FAQResponse(
    val data: List<FAQItems>,
    val message: String,
    val status: String
)