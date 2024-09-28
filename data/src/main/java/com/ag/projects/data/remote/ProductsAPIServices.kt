package com.ag.projects.data.remote

import com.ag.projects.domain.model.ProductsResponse
import com.ag.projects.domain.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsAPIServices {

    @GET("home")
    suspend fun getAllProducts(
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG
    ): ProductsResponse

}