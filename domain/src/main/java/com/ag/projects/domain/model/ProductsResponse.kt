package com.ag.projects.domain.model

data class ProductsResponse(
    val data: List<Data>,
    val message: String,
    val status: String
)