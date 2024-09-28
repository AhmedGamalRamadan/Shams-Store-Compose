package com.ag.projects.domain.model

data class Data(
    val content: List<Content>,
    val have_show_more: Boolean,
    val text: String,
    val type: String
)