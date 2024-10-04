package com.ag.projects.domain.model.qa.tarms_conditon

data class TermsAndConditionResponse(
    val data: List<TermsAndConditionItems>,
    val message: String,
    val status: String
)