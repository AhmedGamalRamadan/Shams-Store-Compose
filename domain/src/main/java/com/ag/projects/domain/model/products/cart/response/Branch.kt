package com.ag.projects.domain.model.products.cart.response

data class Branch(
    val avatar: String,
    val distance: Int,
    val full_name: String,
    val id: Int,
    val lat: Double,
    val lng: Double,
    val location_description: String,
    val phone: String,
    val phone_code: String,
    val phone_complete_form: String,
    val rate_avg: Double
)