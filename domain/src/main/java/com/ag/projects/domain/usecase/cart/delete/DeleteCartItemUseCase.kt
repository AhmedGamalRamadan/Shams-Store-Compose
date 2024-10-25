package com.ag.projects.domain.usecase.cart.delete

import com.ag.projects.domain.model.products.cart.response.ShoppingCartResponse
import com.ag.projects.domain.repository.ProductsRepository
import com.ag.projects.domain.utils.Constants

class DeleteCartItemUseCase(private val productsRepository: ProductsRepository) {


    suspend fun deleteCartItem(
        bearerToken: String?,
        itemId: Int,
        guestToken: String?=null,
        lat: Double = Constants.LAT,
        lng: Double = Constants.LNG,
    ): ShoppingCartResponse = productsRepository.deleteCartItem(
        bearerToken = bearerToken,
        itemId = itemId,
        guestToken = guestToken,
        lat = lat,
        lng = lng
    )
}