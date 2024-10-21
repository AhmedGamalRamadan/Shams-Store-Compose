package com.ag.projects.data.repository

import com.ag.projects.data.remote.ProductsAPIServices
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
import com.ag.projects.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val productsAPIServices: ProductsAPIServices
) : ProductsRepository {

    override suspend fun getAllProducts(): ProductsResponse = productsAPIServices.getAllProducts()

    override suspend fun getAllCategories(): CategoriesResponse =
        productsAPIServices.getAllCategories()

    override suspend fun getAllBrands(): CategoriesResponse =
        productsAPIServices.getAllBrands()

    override suspend fun getPrivacyPolicy(): PolicyDataResponse =
        productsAPIServices.getPrivacyPolicy()

    override suspend fun getTermsAndCondition(): TermsAndConditionResponse =
        productsAPIServices.getTermsAndCondition()

    override suspend fun getAboutUsData(): AboutResponse =
        productsAPIServices.getAboutUsData()

    override suspend fun getFAQData(): FAQResponse =
        productsAPIServices.getFAQData()

    override suspend fun getContactUs(): ContactUsResponse =
        productsAPIServices.getContactUs()

    override suspend fun getAllCountries(): AllCountriesResponse =
        productsAPIServices.getAllCountries()

    /*
    Auth
     */
    override suspend fun login(loginRequest: AuthenticationRequest): LoginResponse =
        productsAPIServices.login(loginRequest)


    override suspend fun verifyOTP(loginRequest: AuthenticationRequest): VerifyResponse =
        productsAPIServices.verifyOTP(loginRequest)

    override suspend fun register(registerRequest: AuthenticationRequest): VerifyResponse =
        productsAPIServices.register(registerRequest)

    override suspend fun getProductsDetails(
        auth: String?,
        productId: Int,
        guestToken: String?,
        lat: Double,
        lng: Double
    ): ProductsResponse = productsAPIServices.getProductsDetails(productId = productId)



}