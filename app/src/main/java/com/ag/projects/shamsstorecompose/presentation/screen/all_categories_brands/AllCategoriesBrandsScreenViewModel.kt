package com.ag.projects.shamsstorecompose.presentation.screen.all_categories_brands

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.products.all_categories.AllProductsResponse
import com.ag.projects.domain.model.products.brand.CategoriesResponse
import com.ag.projects.domain.model.products.home.Content
import com.ag.projects.domain.usecase.products.GetProductsUseCase
import com.ag.projects.domain.usecase.products.all_categories.GetAllCategoriesBrandsUseCase
import com.ag.projects.shamsstorecompose.utils.Result
import com.ag.projects.shamsstorecompose.utils.helper.handleRequest
import com.google.gson.JsonParseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AllCategoriesBrandsScreenViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getAllCategoriesBrandsUseCase: GetAllCategoriesBrandsUseCase
) : ViewModel() {

    private val _allCategoriesHeader = MutableStateFlow<Result<CategoriesResponse>>(Result.Loading)
    val allCategoriesHeader = _allCategoriesHeader.asStateFlow()


    private val _productsBrandCategories =
        MutableStateFlow<Result<List<Content>>>(Result.Loading)
    val productsBrandCategories = _productsBrandCategories.asStateFlow()

    private var currentPage = 1

    fun getAllCategoriesHeader() {
        viewModelScope.launch {
            handleRequest(
                request = {
                    getProductsUseCase.getAllCategories()
                },
                state = _allCategoriesHeader
            )
        }
    }

    fun getCategoriesBrands(
        auth: String?,
        guestToken: String? = null,
        categoryId: Int? = null,
        brandId: Int? = null,
        lat: Double? = null,
        lng: Double? = null,
        type: String? = null,
        productId: Int? = null,
        fromPrice: Float? = null,
        toPrice: Float? = null,
        brandIds: List<Int>? = null,
        deliveryType: String? = null,
        filterType: String? = null,
        sorted: String? = null,
    ) {
        viewModelScope.launch {
            try {
                val newProducts = getAllCategoriesBrandsUseCase.getAllProducts(
                    auth,
                    guestToken,
                    currentPage,
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
                _productsBrandCategories.update { result ->
                    val updatedList =
                        if (currentPage == 1) newProducts.data else (result as? Result.Success)?.data.orEmpty() + newProducts.data
                    Result.Success(updatedList)
                }
            } catch (networkException: IOException) {
                _productsBrandCategories.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _productsBrandCategories.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _productsBrandCategories.emit(Result.Error("Unexpected error", e))
            }
        }
    }

    fun loadNextPage(
        auth: String?,
        guestToken: String? = null,
        categoryId: Int? = null,
        brandId: Int? = null
    ) {
        currentPage++
        getCategoriesBrands(auth, guestToken, categoryId, brandId)
    }

    fun resetPagination() {
        currentPage = 1
    }
}