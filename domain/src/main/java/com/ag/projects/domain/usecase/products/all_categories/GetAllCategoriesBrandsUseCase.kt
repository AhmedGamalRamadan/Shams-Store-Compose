package com.ag.projects.domain.usecase.products.all_categories

import com.ag.projects.domain.model.products.all_categories.AllProductsResponse
import com.ag.projects.domain.repository.ProductsRepository

class GetAllCategoriesBrandsUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getAllProducts(
        auth: String?,
        guestToken: String?,
        page: Int?,
        categoryId: Int?,
        brandId: Int?,
        lat: Double?,
        lng: Double?,
        type: String?,
        productId: Int?,
        fromPrice: Float?,
        toPrice: Float?,
        brandIds: List<Int>?,
        deliveryType: String?,
        filterType: String?,
        sorted: String?,
    ): AllProductsResponse = productsRepository.getAllProducts(
        auth,
        guestToken,
        page,
        categoryId,
        brandId,
        lat,
        lng,
        type,
        productId,
        fromPrice,
        toPrice,
        brandIds,
        deliveryType,
        filterType,
        sorted
    )
}