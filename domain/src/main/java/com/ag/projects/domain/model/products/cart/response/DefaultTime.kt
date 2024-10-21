package com.ag.projects.domain.model.products.cart.response

import com.ag.projects.domain.model.products.cart.response.Day

data class DefaultTime(
    val currency: String,
    val day: Day,
    val from: String,
    val id: Int,
    val is_active: Boolean,
    val is_active_trans: String,
    val is_available: Boolean,
    val is_free: Boolean,
    val is_free_trans: String,
    val price: Int,
    val to: String
)