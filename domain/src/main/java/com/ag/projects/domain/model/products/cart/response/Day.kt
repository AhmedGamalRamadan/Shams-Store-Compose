package com.ag.projects.domain.model.products.cart.response

data class Day(
    val date_at: String,
    val date_at_trans: String,
    val day: String,
    val day_trans: String,
    val id: Int,
    val server_time: String
)