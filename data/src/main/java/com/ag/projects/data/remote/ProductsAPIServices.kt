package com.ag.projects.data.remote

import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.domain.model.auth.login.LoginResponse
import com.ag.projects.domain.model.auth.verify.VerifyResponse
import com.ag.projects.domain.model.country.AllCountriesResponse
import com.ag.projects.domain.model.products.brand.CategoriesResponse
import com.ag.projects.domain.model.products.home.ProductsResponse
import com.ag.projects.domain.model.qa.about.AboutResponse
import com.ag.projects.domain.model.qa.contact_us.ContactUsResponse
import com.ag.projects.domain.model.qa.faq.FAQResponse
import com.ag.projects.domain.model.qa.policy.PolicyDataResponse
import com.ag.projects.domain.model.qa.tarms_conditon.TermsAndConditionResponse
import com.ag.projects.domain.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductsAPIServices {

    @GET(Constants.HOME)
    suspend fun getAllProducts(
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG
    ): ProductsResponse

    @GET(Constants.CATEGORIES)
    suspend fun getAllCategories(
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG
    ): CategoriesResponse

    @GET(Constants.BRAND)
    suspend fun getAllBrands(
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG
    ): CategoriesResponse

    @GET(Constants.POLICY)
    suspend fun getPrivacyPolicy(): PolicyDataResponse

    @GET(Constants.TERMS)
    suspend fun getTermsAndCondition(): TermsAndConditionResponse

    @GET(Constants.ABOUT)
    suspend fun getAboutUsData(): AboutResponse

    @GET(Constants.FAQ)
    suspend fun getFAQData(): FAQResponse

    @GET(Constants.CONTACT)
    suspend fun getContactUs(): ContactUsResponse


    @GET(Constants.GET_COUNTRIES)
    suspend fun getAllCountries(): AllCountriesResponse

    @POST(Constants.LOGIN)
    suspend fun login(@Body loginRequest: AuthenticationRequest): LoginResponse

    @POST(Constants.VERIFY_PHONE)
    suspend fun verifyOTP(@Body loginRequest: AuthenticationRequest): VerifyResponse

    @POST(Constants.REGISTER)
    suspend fun register(@Body registerRequest: AuthenticationRequest): VerifyResponse


}