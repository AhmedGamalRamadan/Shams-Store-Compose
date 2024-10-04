package com.ag.projects.domain.repository

import com.ag.projects.domain.model.brand.CategoriesResponse
import com.ag.projects.domain.model.home.ProductsResponse
import com.ag.projects.domain.model.qa.policy.PolicyDataResponse

interface ProductsRepository {

    suspend fun getAllProducts(): ProductsResponse

    suspend fun getAllCategories(): CategoriesResponse

    suspend fun getAllBrands(): CategoriesResponse

    suspend fun getPrivacyPolicy(): PolicyDataResponse
}