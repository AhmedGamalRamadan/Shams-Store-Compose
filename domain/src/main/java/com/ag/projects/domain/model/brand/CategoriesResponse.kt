package com.ag.projects.domain.model.brand

data class CategoriesResponse(
    val data: List<DataCategories>,
    val message: String,
    val status: String
)