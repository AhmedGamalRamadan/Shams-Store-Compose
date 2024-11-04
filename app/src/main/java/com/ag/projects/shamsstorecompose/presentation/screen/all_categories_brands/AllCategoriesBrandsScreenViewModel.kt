package com.ag.projects.shamsstorecompose.presentation.screen.all_categories_brands

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.products.brand.CategoriesResponse
import com.ag.projects.domain.usecase.products.GetProductsUseCase
import com.ag.projects.shamsstorecompose.utils.Result
import com.ag.projects.shamsstorecompose.utils.helper.handleRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCategoriesBrandsScreenViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _allCategories = MutableStateFlow<Result<CategoriesResponse>>(Result.Loading)
    val allCategories = _allCategories.asStateFlow()

    private val _allBrands = MutableStateFlow<Result<CategoriesResponse>>(Result.Loading)
    val allBrands = _allBrands.asStateFlow()

    fun getAllCategories() {
        viewModelScope.launch {
            handleRequest(
                request = {
                    getProductsUseCase.getAllCategories()
                },
                state = _allCategories
            )
        }
    }

    fun getAllBrands() {
        viewModelScope.launch {
            handleRequest(
                request = {
                    getProductsUseCase.getAllBrands()
                },
                state = _allBrands
            )
        }
    }
}