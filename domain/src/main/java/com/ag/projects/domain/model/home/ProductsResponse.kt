package com.ag.projects.domain.model.home

data class ProductsResponse(
    val data: List<Data>,
    val message: String,
    val status: String
)