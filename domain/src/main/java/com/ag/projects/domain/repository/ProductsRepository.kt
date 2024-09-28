package com.ag.projects.domain.repository

import com.ag.projects.domain.model.ProductsResponse

interface ProductsRepository {

    suspend fun getAllProducts(): ProductsResponse

}