package com.ag.projects.domain.model.products.home

data class ProductsResponse(
    val data: List<Data>,
    val message: String,
    val status: String
)