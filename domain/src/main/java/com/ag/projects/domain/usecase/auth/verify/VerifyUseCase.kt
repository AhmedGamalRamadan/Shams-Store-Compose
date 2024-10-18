package com.ag.projects.domain.usecase.auth.verify

import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.domain.model.auth.verify.VerifyResponse
import com.ag.projects.domain.repository.ProductsRepository

class VerifyUSeCase(private val productsRepository: ProductsRepository) {

    suspend fun verifyOTP(loginRequest: AuthenticationRequest): VerifyResponse =
        productsRepository.verifyOTP(loginRequest)

}