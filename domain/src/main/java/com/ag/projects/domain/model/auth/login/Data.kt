package com.ag.projects.domain.model.auth.login

data class Data(
    val is_active: Boolean,
    val is_ban: Boolean,
    val is_complete: Boolean,
    val is_verify: Boolean,
    val register_complete_step: Int,
    val user_type: String
)