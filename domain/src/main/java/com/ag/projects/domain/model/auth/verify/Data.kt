package com.ag.projects.domain.model.auth.verify

data class Data(
    val avatar: String,
    val cashback: Int,
    val country: Country,
    val email: String,
    val full_name: String,
    val gender: String,
    val id: Int,
    val is_active: Boolean,
    val is_allow_notification: Boolean,
    val is_complete: Boolean,
    val is_verify: Boolean,
    val locale: String,
    val nationality: Nationality,
    val phone: String,
    val phone_code: String,
    val phone_complete_form: String,
    val points: Int,
    val register_complete_step: Int,
    val token: String,
    val user_type: String,
    val wallet: Int
)