package com.ag.projects.domain.model.qa.contact_us

data class ContactUsItems(
    val android_link: String,
    val email: String,
    val ios_link: String,
    val phone: String,
    val project_name: String,
    val social: Social,
    val whatsapp: String
)