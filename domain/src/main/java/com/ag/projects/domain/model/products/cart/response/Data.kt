package com.ag.projects.domain.model.products.cart.response

data class Data(
    val address: String,
    val branch_has_delivery: Boolean,
    val branch_has_pickup: Boolean,
    val cart: Cart,
    val count_of_cart: Int,
    val count_out_of_stock_item: Int,
    val default_time: DefaultTime,
    val distance: Double,
    val express_delivery: Boolean,
    val is_reach_free_delivery: Boolean,
    val order_type: String,
    val out_of_stock_item: Boolean,
    val price_details: PriceDetails
)