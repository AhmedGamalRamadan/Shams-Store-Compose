package com.ag.projects.shamsstorecompose.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.ProductsResponse
import com.ag.projects.domain.usecase.GetProductsUseCase
import com.ag.projects.shamsstorecompose.utils.Result
import com.google.gson.JsonParseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _allProducts =
        MutableStateFlow<ProductsResponse?>(null)
    val allProducts = _allProducts.asStateFlow()

    init {
        getAllProducts()
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

}