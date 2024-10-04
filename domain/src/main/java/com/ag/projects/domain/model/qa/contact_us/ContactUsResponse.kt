package com.ag.projects.domain.model.qa.contact_us

data class ContactUsResponse(
    val data: ContactUsItems,
    val message: String,
    val status: String
)