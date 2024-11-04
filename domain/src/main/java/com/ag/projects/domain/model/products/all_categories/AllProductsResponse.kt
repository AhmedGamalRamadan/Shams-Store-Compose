package com.ag.projects.domain.model.products.all_categories

import com.ag.projects.domain.model.products.home.Content


data class AllProductsResponse(
    val data: List<Content>,
    val links: Links,
    val message: String,
    val meta: Meta,
    val status: String
)