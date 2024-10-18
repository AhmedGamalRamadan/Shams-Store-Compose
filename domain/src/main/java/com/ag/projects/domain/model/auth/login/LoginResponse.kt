package com.ag.projects.domain.model.auth.login

data class LoginResponse(
    val data: Data,
    val message: String,
    val status: String
)