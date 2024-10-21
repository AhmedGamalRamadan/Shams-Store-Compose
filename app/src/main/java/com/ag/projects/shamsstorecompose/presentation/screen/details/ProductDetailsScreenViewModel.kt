package com.ag.projects.shamsstorecompose.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.products.home.ProductsResponse
import com.ag.projects.domain.usecase.details.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.ag.projects.shamsstorecompose.utils.Result
import com.google.gson.JsonParseException
import kotlinx.coroutines.launch
import java.io.IOException

@HiltViewModel
class ProductDetailsScreenViewModel @Inject constructor(
    private val productDetailsUseCase: GetProductDetailsUseCase,
) : ViewModel() {

    private val _productDetailsState =
        MutableStateFlow<Result<ProductsResponse>>(Result.Loading)
    val productDetailsState = _productDetailsState.asStateFlow()


    fun getProductDetails(productId: Int) {
        viewModelScope.launch {
            try {
                _productDetailsState.emit(
                    Result.Success(
                        productDetailsUseCase.getProductsDetails(
                            productId = productId
                        )
                    )
                )
            } catch (networkException: IOException) {
                _productDetailsState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _productDetailsState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _productDetailsState.emit(Result.Error("Unexpected error", e))
            }
        }
    }
}