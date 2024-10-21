package com.ag.projects.domain.model.products.cart.response

data class PriceDetails(
    val coupon_price: Int,
    val currency: String,
    val delivery_price: Int,
    val free_delivery: Int,
    val limit_for_total_order_price: String,
    val reach_free_delivery: Double,
    val total_price: Double,
    val total_product: Double,
    val vat_percentage: Int,
    val vat_price: Double
)