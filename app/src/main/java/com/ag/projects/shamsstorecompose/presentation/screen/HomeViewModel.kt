package com.ag.projects.shamsstorecompose.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.products.brand.CategoriesResponse
import com.ag.projects.domain.model.products.cart.AddToCartRequest
import com.ag.projects.domain.model.products.cart.response.ShoppingCartResponse
import com.ag.projects.domain.model.products.home.ProductsResponse
import com.ag.projects.domain.usecase.cart.add.AddToCartUseCase
import com.ag.projects.domain.usecase.products.GetProductsUseCase
import com.google.gson.JsonParseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import com.ag.projects.shamsstorecompose.utils.Result


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsUseCase: GetProductsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : ViewModel() {

    private val _allProducts =
        MutableStateFlow<Result<ProductsResponse>>(Result.Loading)
    val allProducts = _allProducts.asStateFlow()

    private val _categoriesState = MutableStateFlow<Result<CategoriesResponse>>(Result.Loading)
    val categoriesState = _categoriesState.asStateFlow()

    private val _brandsState = MutableStateFlow<Result<CategoriesResponse>>(Result.Loading)
    val brandsState = _brandsState.asStateFlow()


    private val _addToCartState = MutableStateFlow<Result<ShoppingCartResponse>>(Result.Loading)
    val addToCartState = _addToCartState.asStateFlow()

    init {
        getAllProducts()
    }


    private fun getAllProducts() {
        viewModelScope.launch {
            try {
                val productsResponse = productsUseCase.getAllProductsRemote()
                _allProducts.emit(Result.Success(productsResponse))
            } catch (networkException: IOException) {
                _allProducts.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _allProducts.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _allProducts.emit(Result.Error("Unexpected error", e))
            }
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            try {
                val productsResponse = productsUseCase.getAllCategories()
                _categoriesState.emit(Result.Success(productsResponse))
            } catch (networkException: IOException) {
                _categoriesState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _categoriesState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _categoriesState.emit(Result.Error("Unexpected error", e))
            }
        }
    }

     fun getAllBrands() {
        viewModelScope.launch {
            try {
                val productsResponse = productsUseCase.getAllBrands()
                _brandsState.emit(Result.Success(productsResponse))
            } catch (networkException: IOException) {
                _brandsState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _brandsState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _brandsState.emit(Result.Error("Unexpected error", e))
            }
        }
    }


    fun addToCart(
        bearerToken: String,
        guestToken: String?=null,
        addToCartRequest: AddToCartRequest
    ) {
        viewModelScope.launch {
            try {
                val response = addToCartUseCase.addToCarts(
                    bearerToken = bearerToken,
                    guestToken = guestToken,
                    addToCartRequest = addToCartRequest
                )
                _addToCartState.emit(Result.Success(response))
            } catch (networkException: IOException) {
                _addToCartState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _addToCartState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _addToCartState.emit(Result.Error("Unexpected error", e))
            }
        }
    }


}