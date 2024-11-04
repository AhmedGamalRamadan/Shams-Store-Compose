package com.ag.projects.domain.model.products.all_categories

import com.ag.projects.domain.model.products.all_categories.Link

data class Meta(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val links: List<Link>,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)