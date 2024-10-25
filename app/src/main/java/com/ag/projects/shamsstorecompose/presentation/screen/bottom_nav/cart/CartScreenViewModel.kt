package com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.products.cart.AddToCartRequest
import com.ag.projects.domain.model.products.cart.response.ShoppingCartResponse
import com.ag.projects.domain.usecase.cart.add.AddToCartUseCase
import com.ag.projects.domain.usecase.cart.delete.DeleteAllCartsUseCase
import com.ag.projects.domain.usecase.cart.delete.DeleteCartItemUseCase
import com.ag.projects.domain.usecase.cart.get.GetShoppingCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.ag.projects.shamsstorecompose.utils.Result
import com.google.gson.JsonParseException
import kotlinx.coroutines.launch
import java.io.IOException

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val getShoppingCartUseCase: GetShoppingCartUseCase,
    private val deleteAllCartsUseCase: DeleteAllCartsUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private val _getCartItems = MutableStateFlow<Result<ShoppingCartResponse>>(Result.Loading)
    val getCartItems = _getCartItems.asStateFlow()

    private val _deleteAllCartState = MutableStateFlow<Result<ShoppingCartResponse>>(Result.Loading)
    val deleteAllCartState = _deleteAllCartState.asStateFlow()

    private val _deleteCartItemState =
        MutableStateFlow<Result<ShoppingCartResponse>>(Result.Loading)
    val deleteCartItemState = _deleteCartItemState.asStateFlow()

    private val _addToCartState = MutableStateFlow<Result<ShoppingCartResponse>>(Result.Loading)
    val addToCartState = _addToCartState.asStateFlow()


    fun getCarts(
        bearerToken: String,
        addressId: Int,
        isPicked: Int,
        branchWorkTimeId: Int
    ) {
        viewModelScope.launch {
            try {
                val cart = getShoppingCartUseCase.getCarts(
                    bearerToken = bearerToken,
                    addressId = addressId,
                    isPicked = isPicked,
                    branchWorkTimeId = branchWorkTimeId
                )
                _getCartItems.emit(Result.Success(cart))
            } catch (networkException: IOException) {
                _getCartItems.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _getCartItems.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _getCartItems.emit(Result.Error("Unexpected error", e))
            }
        }
    }

    fun deleteAllCart(
        bearerToken: String,
        guestToken: String,
    ) {
        viewModelScope.launch {
            try {
                val response = deleteAllCartsUseCase.deleteAllCarts(
                    bearerToken = bearerToken,
                    guestToken = guestToken
                )
                _deleteAllCartState.emit(Result.Success(response))
            } catch (networkException: IOException) {
                _deleteAllCartState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _deleteAllCartState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _deleteAllCartState.emit(Result.Error("Unexpected error", e))
            }
        }
    }

    fun deleteCartItem(
        bearerToken: String,
        productId: Int,
        guestToken: String,
    ) {
        viewModelScope.launch {
            try {
                val response = deleteCartItemUseCase.deleteCartItem(
                    bearerToken = bearerToken,
                    itemId = productId,
                    guestToken = guestToken
                )
                _deleteCartItemState.emit(Result.Success(response))
            } catch (networkException: IOException) {
                _deleteCartItemState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _deleteCartItemState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _deleteCartItemState.emit(Result.Error("Unexpected error", e))
            }
        }
    }

    fun addToCart(
        bearerToken: String,
        guestToken: String,
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
/*
private suspend fun <T> handleRequest(request: suspend () -> T, state: MutableStateFlow<Result<T>>) {
    try {
        state.emit(Result.Success(request()))
    } catch (networkException: IOException) {
        state.emit(Result.Error("Network error", networkException))
    } catch (jsonException: JsonParseException) {
        state.emit(Result.Error("Data parsing error", jsonException))
    } catch (e: Exception) {
        state.emit(Result.Error("Unexpected error", e))
    }
}

fun addToCart(...) {
    viewModelScope.launch {
        handleRequest({
            addToCartUseCase.addToCarts(bearerToken, guestToken, addToCartRequest)
        }, _addToCartState)
    }
}

 */