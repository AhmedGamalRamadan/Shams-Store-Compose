package com.ag.projects.data.repository

import com.ag.projects.data.remote.ProductsAPIServices
import com.ag.projects.domain.model.brand.CategoriesResponse
import com.ag.projects.domain.model.home.ProductsResponse
import com.ag.projects.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val productsAPIServices: ProductsAPIServices
) : ProductsRepository {

    override suspend fun getAllProducts(): ProductsResponse = productsAPIServices.getAllProducts()

    override suspend fun getAllCategories(): CategoriesResponse =
        productsAPIServices.getAllCategories()

    override suspend fun getAllBrands(): CategoriesResponse =
        productsAPIServices.getAllBrands()
}