package com.ag.projects.domain.model.auth.login

data class AuthenticationRequest(
    val country_id: Int? = null,
    val phone: String? = null,
    val device_token: String? = null,
    val guest_token: String = "222",
    val type: String = "android",
    val code: String? = null,
    val full_name: String? = null,
    val email: String? = null,
)