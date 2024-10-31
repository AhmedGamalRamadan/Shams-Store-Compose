package com.ag.projects.data.remote

import com.ag.projects.domain.model.address.AddAddressResponse
import com.ag.projects.domain.model.address.AddressesResponse
import com.ag.projects.domain.model.address.CreateAddressRequest
import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.domain.model.auth.login.LoginResponse
import com.ag.projects.domain.model.auth.verify.VerifyResponse
import com.ag.projects.domain.model.country.AllCountriesResponse
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
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
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

    /*
    Auth
     */
    @POST(Constants.LOGIN)
    suspend fun login(@Body loginRequest: AuthenticationRequest): LoginResponse

    @POST(Constants.VERIFY_PHONE)
    suspend fun verifyOTP(@Body loginRequest: AuthenticationRequest): VerifyResponse

    @POST(Constants.REGISTER)
    suspend fun register(@Body registerRequest: AuthenticationRequest): VerifyResponse

    /*
    Product Details
     */
    @GET("products/{id}")
    suspend fun getProductsDetails(
        @Header("Authorization") auth: String? = null,
        @Path("id") productId: Int,
        @Query("guest_token") guestToken: String? = null,
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double? = Constants.LNG
    ): ProductsResponse

    /*
    Shopping Cart
     */

    @GET(Constants.CARTS)
    suspend fun getCarts(
        @Header("Authorization") bearerToken: String?,
        @Query("guest_token") guestToken: String? = null,
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG,
        @Query("address_id") addressId: Int? = null,
        @Query("is_picked") isPicked: Int? = null,
        @Query("branch_work_time_id") branchWorkTimeId: Int? = null
    ): ShoppingCartResponse

    @POST(Constants.CARTS)
    suspend fun addToCarts(
        @Header("Authorization") bearerToken: String?,
        @Query("guest_token") guestToken: String? = null,
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG,
        @Body addToCartRequest: AddToCartRequest,
    ): ShoppingCartResponse

    @DELETE(Constants.CARTS)
    suspend fun deleteAllCarts(
        @Header("Authorization") bearerToken: String?,
        @Query("guest_token") guestToken: String? = null,
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG,
    ): ShoppingCartResponse

    @DELETE("carts/item/{itemId}")
    suspend fun deleteCartItem(
        @Header("Authorization") bearerToken: String?,
        @Path("itemId") itemId: Int,
        @Query("guest_token") guestToken: String? = null,
        @Query("lat") lat: Double = Constants.LAT,
        @Query("lng") lng: Double = Constants.LNG,
    ): ShoppingCartResponse


    /*
  Addresses

   */
    @POST(Constants.CREATE_ADDRESS)
    suspend fun createAddress(
        @Header("Authorization") auth: String?,
        @Query("guest_token") guestToken: String?,
        @Body createAddressRequest: CreateAddressRequest?,
    ): AddAddressResponse


    @GET(Constants.ADDRESSES)
    suspend fun getAddresses(
        @Header("Authorization") auth: String?,
        @Query("guest_token") guestToken: String?,
    ): AddressesResponse


    @DELETE(Constants.REMOVE_ADDRESS)
    suspend fun removeAddress(
        @Header("Authorization") auth: String?,
        @Path("id") addressId: String,
        @Query("guest_token") guestToken: String?,
    ): AddressesResponse


    @POST(Constants.UPDATE_ADDRESS)
    suspend fun updateAddress(
        @Header("Authorization") auth: String?,
        @Path("id") addressId: String,
        @Query("guest_token") guestToken: String?,
        @Body createAddressRequest: CreateAddressRequest?,
    ): AddAddressResponse

    @FormUrlEncoded
    @POST(Constants.IS_DEFAULT_ADDRESS)
    suspend fun isDefaultAddress(
        @Header("Authorization") auth: String?,
        @Path("id") addressId: String,
        @Query("guest_token") guestToken: String?,
        @Field("is_default") isDefaultRequest: Int?,
    ): AddAddressResponse
}