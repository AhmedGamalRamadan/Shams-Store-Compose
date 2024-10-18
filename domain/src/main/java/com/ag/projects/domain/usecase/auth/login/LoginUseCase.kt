package com.ag.projects.domain.usecase.auth.login

import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.domain.model.auth.login.LoginResponse
import com.ag.projects.domain.model.country.AllCountriesResponse
import com.ag.projects.domain.repository.ProductsRepository

class LoginUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getAllCountries(): AllCountriesResponse = productsRepository.getAllCountries()

    suspend fun login(loginRequest: AuthenticationRequest): LoginResponse =
        productsRepository.login(loginRequest)



}