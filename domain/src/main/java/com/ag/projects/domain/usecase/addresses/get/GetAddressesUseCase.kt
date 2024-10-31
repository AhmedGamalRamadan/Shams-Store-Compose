package com.ag.projects.domain.usecase.addresses.get

import com.ag.projects.domain.model.address.AddressesResponse
import com.ag.projects.domain.repository.ProductsRepository

class GetAddressesUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getAddresses(
        auth: String?,
        guestToken: String?,
    ): AddressesResponse = productsRepository.getAddresses(
        auth,
        guestToken,
    )
}