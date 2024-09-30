package com.ag.projects.shamsstorecompose.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.brand.CategoriesResponse
import com.ag.projects.domain.model.home.ProductsResponse
import com.ag.projects.domain.usecase.GetProductsUseCase
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
    private val productsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _allProducts =
        MutableStateFlow<ProductsResponse?>(null)
    val allProducts = _allProducts.asStateFlow()

    private val _categoriesState = MutableStateFlow<Result<CategoriesResponse>>(Result.Loading)
     val categoriesState = _categoriesState.asStateFlow()


    init {
        getAllProducts()
        getAllCategories()
    }


    private fun getAllProducts() {
        viewModelScope.launch {
            try {
                val productsResponse = productsUseCase.getAllProductsRemote()
                _allProducts.emit(productsResponse)
            } catch (_: Exception) {

            }
        }
    }

    private fun getAllCategories() {
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


}