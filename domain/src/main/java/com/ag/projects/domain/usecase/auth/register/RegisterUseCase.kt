package com.ag.projects.domain.usecase.auth.register

import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.domain.model.auth.verify.VerifyResponse
import com.ag.projects.domain.repository.ProductsRepository

class RegisterUseCase(private val productsRepository: ProductsRepository) {

    suspend fun register(registerRequest: AuthenticationRequest): VerifyResponse =
        productsRepository.register(registerRequest)

}