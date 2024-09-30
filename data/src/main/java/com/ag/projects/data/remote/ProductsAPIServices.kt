package com.ag.projects.data.remote

import com.ag.projects.domain.model.brand.CategoriesResponse
import com.ag.projects.domain.model.home.ProductsResponse
import com.ag.projects.domain.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsAPIServices {

    @GET("home")
    suspend fun getAllProducts(
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG
    ): ProductsResponse

    @GET("categories")
    suspend fun getAllCategories(
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG
    ): CategoriesResponse

    @GET("brands")
    suspend fun getAllBrands(
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG
    ): CategoriesResponse


}