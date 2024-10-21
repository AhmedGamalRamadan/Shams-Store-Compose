package com.ag.projects.domain.model.products.cart.response

data class Item(
    val id: Int,
    val offer_discount_value: Int,
    val offer_type: Any,
    val product_detail: ProductDetail,
    var quantity: Int,
    val total_price: Double
)