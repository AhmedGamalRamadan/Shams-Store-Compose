package com.ag.projects.domain.usecase.cart.delete

import com.ag.projects.domain.model.products.cart.response.ShoppingCartResponse
import com.ag.projects.domain.repository.ProductsRepository
import com.ag.projects.domain.utils.Constants

class DeleteAllCartsUseCase(private val productsRepository: ProductsRepository) {

    suspend fun deleteAllCarts(
        bearerToken: String?,
        guestToken: String?,
        lat: Double = Constants.LAT,
        lng: Double = Constants.LNG,
    ): ShoppingCartResponse = productsRepository.deleteAllCarts(
        bearerToken = bearerToken,
        guestToken = guestToken,
        lat = lat,
        lng = lng
    )
}