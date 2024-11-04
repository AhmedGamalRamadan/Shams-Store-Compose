package com.ag.projects.shamsstorecompose.presentation.screen.all_categories_brands

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.products.all_categories.AllProductsResponse
import com.ag.projects.domain.model.products.brand.CategoriesResponse
import com.ag.projects.domain.usecase.products.GetProductsUseCase
import com.ag.projects.domain.usecase.products.all_categories.GetAllCategoriesBrandsUseCase
import com.ag.projects.shamsstorecompose.utils.Result
import com.ag.projects.shamsstorecompose.utils.helper.handleRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCategoriesBrandsScreenViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getAllCategoriesBrandsUseCase: GetAllCategoriesBrandsUseCase
) : ViewModel() {

    private val _allCategoriesHeader = MutableStateFlow<Result<CategoriesResponse>>(Result.Loading)
    val allCategoriesHeader = _allCategoriesHeader.asStateFlow()


    private val _productsBrandCategories = MutableStateFlow<Result<AllProductsResponse>>(Result.Loading)
    val productsBrandCategories = _productsBrandCategories.asStateFlow()


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
    ){
        viewModelScope.launch {
            handleRequest(
                request={
                    getAllCategoriesBrandsUseCase.getAllProducts(
                        auth,
                        guestToken,
                        1,
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
                },
                state = _productsBrandCategories
            )
        }
    }
}