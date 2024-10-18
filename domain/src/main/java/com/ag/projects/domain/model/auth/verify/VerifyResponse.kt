package com.ag.projects.domain.model.auth.verify


data class VerifyResponse(
    val data: Data,
    val message: String,
    val status: String
)