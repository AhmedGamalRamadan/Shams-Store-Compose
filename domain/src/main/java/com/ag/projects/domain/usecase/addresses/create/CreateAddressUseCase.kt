package com.ag.projects.domain.usecase.addresses.create

import com.ag.projects.domain.model.address.AddAddressResponse
import com.ag.projects.domain.model.address.CreateAddressRequest
import com.ag.projects.domain.repository.ProductsRepository
class CreateAddressUseCase(private val productsRepository: ProductsRepository) {

    suspend fun createAddress(
        auth: String?,
        guestToken: String?,
        createAddressRequest: CreateAddressRequest?,
    ): AddAddressResponse = productsRepository.createAddress(auth, guestToken, createAddressRequest)
}