package com.ag.projects.domain.repository

import com.ag.projects.domain.model.address.AddAddressResponse
import com.ag.projects.domain.model.address.AddressesResponse
import com.ag.projects.domain.model.address.CreateAddressRequest
import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.domain.model.auth.login.LoginResponse
import com.ag.projects.domain.model.auth.verify.VerifyResponse
import com.ag.projects.domain.model.country.AllCountriesResponse
import com.ag.projects.domain.model.products.all_categories.AllProductsResponse
import com.ag.projects.domain.model.products.brand.CategoriesResponse
import com.ag.projects.domain.model.products.cart.AddToCartRequest
import com.ag.projects.domain.model.products.cart.response.ShoppingCartResponse
import com.ag.projects.domain.model.products.home.ProductsResponse
import com.ag.projects.domain.model.qa.about.AboutResponse
import com.ag.projects.domain.model.qa.contact_us.ContactUsResponse
import com.ag.projects.domain.model.qa.faq.FAQResponse
import com.ag.projects.domain.model.qa.policy.PolicyDataResponse
import com.ag.projects.domain.model.qa.tarms_conditon.TermsAndConditionResponse
import com.ag.projects.domain.utils.Constants

interface ProductsRepository {

    suspend fun getAllProducts(): ProductsResponse

    suspend fun getAllCategories(): CategoriesResponse

    suspend fun getAllBrands(): CategoriesResponse

    suspend fun getPrivacyPolicy(): PolicyDataResponse

    suspend fun getTermsAndCondition(): TermsAndConditionResponse

    suspend fun getAboutUsData(): AboutResponse

    suspend fun getFAQData(): FAQResponse

    suspend fun getContactUs(): ContactUsResponse

    suspend fun getAllCountries(): AllCountriesResponse

    suspend fun login(
        loginRequest: AuthenticationRequest
    ): LoginResponse

    suspend fun verifyOTP(loginRequest: AuthenticationRequest): VerifyResponse

    suspend fun register(registerRequest: AuthenticationRequest): VerifyResponse

    suspend fun getProductsDetails(
        auth: String? = null,
        productId: Int,
        guestToken: String? = null,
        lat: Double = Constants.LAT,
        lng: Double = Constants.LNG
    ): ProductsResponse

    /*
    Shopping cart
     */
    suspend fun getCarts(
        bearerToken: String?,
        guestToken: String? = null,
        lat: Double = Constants.LAT,
        lng: Double = Constants.LNG,
        addressId: Int? = null,
        isPicked: Int? = null,
        branchWorkTimeId: Int? = null
    ): ShoppingCartResponse

    suspend fun addToCarts(
        bearerToken: String?,
        guestToken: String?,
        lat: Double = Constants.LAT,
        lng: Double = Constants.LNG,
        addToCartRequest: AddToCartRequest,
    ): ShoppingCartResponse

    suspend fun deleteAllCarts(
        bearerToken: String?,
        guestToken: String?,
        lat: Double = Constants.LAT,
        lng: Double = Constants.LNG,
    ): ShoppingCartResponse

    suspend fun deleteCartItem(
        bearerToken: String?,
        itemId: Int,
        guestToken: String?,
        lat: Double = Constants.LAT,
        lng: Double = Constants.LNG,
    ): ShoppingCartResponse

    /*
   Addresses
    */

    suspend fun getAddresses(
        auth: String?,
        guestToken: String?,
    ): AddressesResponse

    suspend fun createAddress(
        auth: String?,
        guestToken: String?,
        createAddressRequest: CreateAddressRequest?,
    ): AddAddressResponse


    suspend fun removeAddress(
        auth: String?,
        addressId: String,
        guestToken: String?,
    ): AddressesResponse


    suspend fun updateAddress(
        auth: String?,
        addressId: String,
        guestToken: String?,
        createAddressRequest: CreateAddressRequest?,
    ): AddAddressResponse

    suspend fun isDefaultAddress(
        auth: String?,
        addressId: String,
        guestToken: String?,
        isDefaultRequest: Int?,
    ): AddAddressResponse

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
    ): AllProductsResponse

}