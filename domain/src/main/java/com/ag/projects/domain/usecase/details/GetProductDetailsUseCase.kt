package com.ag.projects.domain.usecase.details

import com.ag.projects.domain.model.products.home.ProductsResponse
import com.ag.projects.domain.repository.ProductsRepository
import com.ag.projects.domain.utils.Constants

class GetProductDetailsUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getProductsDetails(
        auth: String? = null,
        productId: Int,
        guestToken: String? = null,
        lat: Double = Constants.LAT,
        lng: Double? = Constants.LNG
    ): ProductsResponse = productsRepository.getProductsDetails(productId = productId)
}