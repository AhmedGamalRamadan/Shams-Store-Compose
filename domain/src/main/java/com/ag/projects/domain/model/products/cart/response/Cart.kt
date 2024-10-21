package com.ag.projects.domain.model.products.cart.response

data class Cart(
    val branch: Branch,
    val id: Int,
    val items: List<Item>,
    val prescription_requirement: Boolean
)