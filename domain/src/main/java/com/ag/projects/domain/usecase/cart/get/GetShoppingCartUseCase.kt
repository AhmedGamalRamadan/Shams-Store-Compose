package com.ag.projects.domain.usecase.cart.get

import com.ag.projects.domain.model.products.cart.response.ShoppingCartResponse
import com.ag.projects.domain.repository.ProductsRepository
import com.ag.projects.domain.utils.Constants

class GetShoppingCartUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getCarts(
        bearerToken: String?,
        guestToken: String? = null,
        lat: Double = Constants.LAT,
        lng: Double = Constants.LNG,
        addressId: Int? = null,
        isPicked: Int? = null,
        branchWorkTimeId: Int? = null
    ): ShoppingCartResponse = productsRepository.getCarts(
        bearerToken,
        guestToken,
        lat,
        lng,
        addressId,
        isPicked,
        branchWorkTimeId
    )
}