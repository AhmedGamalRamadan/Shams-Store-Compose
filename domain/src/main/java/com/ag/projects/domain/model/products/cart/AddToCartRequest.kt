package com.ag.projects.domain.model.products.cart


data class AddToCartRequest(
    val product_id: Int,
    val quantity: Int
)
