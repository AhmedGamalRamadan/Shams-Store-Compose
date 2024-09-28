package com.ag.projects.domain.usecase

import com.ag.projects.domain.model.ProductsResponse
import com.ag.projects.domain.repository.ProductsRepository

class GetProductsUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getAllProductsRemote(): ProductsResponse = productsRepository.getAllProducts()
}