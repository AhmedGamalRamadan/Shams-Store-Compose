package com.ag.projects.domain.usecase.addresses.update

import com.ag.projects.domain.model.address.AddAddressResponse
import com.ag.projects.domain.model.address.CreateAddressRequest
import com.ag.projects.domain.repository.ProductsRepository
class UpdateAddressUseCase(private val productsRepository: ProductsRepository) {

    suspend fun updateAddress(
        auth: String?,
        addressId: String,
        guestToken: String?,
        createAddressRequest: CreateAddressRequest?,
    ): AddAddressResponse = productsRepository.updateAddress(
        auth, addressId, guestToken, createAddressRequest
    )
}