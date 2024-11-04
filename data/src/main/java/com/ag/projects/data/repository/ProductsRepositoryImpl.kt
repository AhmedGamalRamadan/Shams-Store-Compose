package com.ag.projects.data.repository

import com.ag.projects.data.remote.ProductsAPIServices
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
import com.ag.projects.domain.repository.ProductsRepository
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

    /*
    Shopping cart
     */

    override suspend fun getCarts(
        bearerToken: String?,
        guestToken: String?,
        lat: Double,
        lng: Double,
        addressId: Int?,
        isPicked: Int?,
        branchWorkTimeId: Int?
    ): ShoppingCartResponse = productsAPIServices.getCarts(
        bearerToken,
        guestToken,
        lat,
        lng,
        addressId,
        isPicked,
        branchWorkTimeId
    )

    override suspend fun addToCarts(
        bearerToken: String?,
        guestToken: String?,
        lat: Double,
        lng: Double,
        addToCartRequest: AddToCartRequest
    ): ShoppingCartResponse = productsAPIServices.addToCarts(
        bearerToken = bearerToken,
        guestToken = guestToken,
        lat = lat,
        lng = lng,
        addToCartRequest = addToCartRequest
    )

    override suspend fun deleteAllCarts(
        bearerToken: String?,
        guestToken: String?,
        lat: Double,
        lng: Double
    ): ShoppingCartResponse = productsAPIServices.deleteAllCarts(
        bearerToken = bearerToken,
        guestToken = guestToken,
        lat = lat,
        lng = lng
    )

    override suspend fun deleteCartItem(
        bearerToken: String?,
        itemId: Int,
        guestToken: String?,
        lat: Double,
        lng: Double
    ): ShoppingCartResponse = productsAPIServices.deleteCartItem(
        bearerToken = bearerToken,
        itemId = itemId,
        guestToken = guestToken,
        lat = lat,
        lng = lng
    )

    /*
Addresses
 */

    override suspend fun getAddresses(auth: String?, guestToken: String?): AddressesResponse =
        productsAPIServices.getAddresses(
            auth,
            guestToken,
        )

    override suspend fun createAddress(
        auth: String?,
        guestToken: String?,
        createAddressRequest: CreateAddressRequest?
    ): AddAddressResponse =
        productsAPIServices.createAddress(auth, guestToken, createAddressRequest)


    override suspend fun removeAddress(
        auth: String?,
        addressId: String,
        guestToken: String?
    ): AddressesResponse = productsAPIServices.removeAddress(auth, addressId, guestToken)


    override suspend fun updateAddress(
        auth: String?,
        addressId: String,
        guestToken: String?,
        createAddressRequest: CreateAddressRequest?
    ): AddAddressResponse = productsAPIServices.updateAddress(
        auth, addressId, guestToken, createAddressRequest
    )

    override suspend fun isDefaultAddress(
        auth: String?,
        addressId: String,
        guestToken: String?,
        isDefaultRequest: Int?
    ): AddAddressResponse = productsAPIServices.isDefaultAddress(
        auth, addressId, guestToken, isDefaultRequest
    )

    override suspend fun getAllProducts(
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
        sorted: String?
    ): AllProductsResponse = productsAPIServices.getAllProducts(
        auth,
        guestToken,
        page,
        categoryId,
        brandId,
        lat,
        lng,
        type,
        productId,
        fromPrice,
        toPrice,
        brandIds,
        deliveryType,
        filterType,
        sorted
    )

}