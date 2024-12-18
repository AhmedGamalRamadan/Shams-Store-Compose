package com.ag.projects.domain.model.products.home



data class Content(
    val available_quantity: Int,
    val brand: Brand,
    val currency: String,
    val desc: String,
    val has_products: Boolean,
    val id: Int,
    val image: String,
    val in_cart: Boolean,
    val in_cart_count: Int,
    val is_express: Boolean,
    val is_fav: Boolean,
    val limit_order_num: Int,
    val name: String,
    val parent: Any,
    val percentage: Any,
    val prescription_requirement: Boolean,
    val price: Double,
    val price_after: Any,
    val product_id_in_cart: Any,
    val quantity: Any,
    val rate_avg: Int,
    val total_price: Int,
    val type: Any,
    val type_trans: Any,
    val code: String,
    val is_replace: Boolean,
    val points: Int,
    val replace_trans: String,
)