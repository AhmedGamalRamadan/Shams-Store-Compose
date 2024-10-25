package com.ag.projects.domain.usecase.cart.add

import com.ag.projects.domain.model.products.cart.AddToCartRequest
import com.ag.projects.domain.model.products.cart.response.ShoppingCartResponse
import com.ag.projects.domain.repository.ProductsRepository
import com.ag.projects.domain.utils.Constants

class AddToCartUseCase(private val productsRepository: ProductsRepository) {

    suspend fun addToCarts(
        bearerToken: String?,
        guestToken: String?,
        lat: Double = Constants.LAT,
        lng: Double = Constants.LNG,
        addToCartRequest: AddToCartRequest,
    ): ShoppingCartResponse = productsRepository.addToCarts(
        bearerToken = bearerToken,
        guestToken = guestToken,
        lat = lat,
        lng = lng,
        addToCartRequest = addToCartRequest
    )
}