package com.ag.projects.domain.model.qa.policy

data class PolicyDataResponse(
    val data: List<PolicyItems>,
    val message: String,
    val status: String
)