package com.ag.projects.domain.usecase.addresses.is_default

import com.ag.projects.domain.model.address.AddAddressResponse
import com.ag.projects.domain.repository.ProductsRepository


class IsDefaultAddressUseCase(private val productsRepository: ProductsRepository) {

    suspend fun isDefaultAddress(
        auth: String?,
        addressId: String,
        guestToken: String?,
        isDefaultRequest: Int?,
    ): AddAddressResponse = productsRepository.isDefaultAddress(
        auth,
        addressId,
        guestToken,
        isDefaultRequest
    )
}