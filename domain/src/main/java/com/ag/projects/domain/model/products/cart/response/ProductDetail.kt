package com.ag.projects.domain.model.products.cart.response


data class ProductDetail(
    val available_quantity: Int,
    val brand: Brand,
    val currency: String,
    val desc: String,
    val id: Int,
    val image: String,
    val in_cart: Boolean,
    val in_cart_count: Int,
    val is_express: Boolean,
    val is_fav: Boolean,
    val limit_order_num: Int,
    val name: String,
    val percentage: Any,
    val prescription_requirement: Boolean,
    val price: Double,
    val price_after: Any,
    val product_id_in_cart: Int,
    val quantity: Any,
    val rate_avg: Int,
    val total_price: Int,
    val type: Any,
    val type_trans: Any
)