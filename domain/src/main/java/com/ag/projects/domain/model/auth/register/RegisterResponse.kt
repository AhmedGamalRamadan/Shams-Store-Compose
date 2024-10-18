package com.ag.projects.domain.model.auth.register


data class RegisterResponse(
    val data: Data,
    val message: String,
    val status: String
)