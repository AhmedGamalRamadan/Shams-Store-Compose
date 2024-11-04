package com.ag.projects.domain.usecase.products

import com.ag.projects.domain.model.products.home.ProductsResponse
import com.ag.projects.domain.repository.ProductsRepository

class GetProductsUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getAllProductsRemote(): ProductsResponse = productsRepository.getAllProducts()

    suspend fun getAllCategories() = productsRepository.getAllCategories()

    suspend fun getAllBrands() = productsRepository.getAllBrands()

}