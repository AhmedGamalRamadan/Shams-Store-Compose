package com.ag.projects.domain.usecase.addresses.remove

import com.ag.projects.domain.model.address.AddressesResponse
import com.ag.projects.domain.repository.ProductsRepository

class RemoveAddressUseCase(private val productsRepository: ProductsRepository) {

    suspend fun removeAddress(
        auth: String?,
        addressId: String,
        guestToken: String?,
    ): AddressesResponse =productsRepository.removeAddress(auth,addressId,guestToken)
}