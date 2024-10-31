package com.ag.projects.shamsstorecompose.presentation.screen.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.address.AddAddressResponse
import com.ag.projects.domain.model.address.AddressesResponse
import com.ag.projects.domain.usecase.addresses.get.GetAddressesUseCase
import com.ag.projects.domain.usecase.addresses.is_default.IsDefaultAddressUseCase
import com.ag.projects.domain.usecase.addresses.remove.RemoveAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.ag.projects.shamsstorecompose.utils.Result
import com.ag.projects.shamsstorecompose.utils.helper.handleRequest
import com.google.gson.JsonParseException
import kotlinx.coroutines.launch
import java.io.IOException


@HiltViewModel
class AddressScreenViewModel @Inject constructor(
    private val getAddressUseCase: GetAddressesUseCase,
    private val removeAddressUseCase: RemoveAddressUseCase,
    private val isDefaultAddressUseCase: IsDefaultAddressUseCase
) : ViewModel() {


    private val _getAddresses = MutableStateFlow<Result<AddressesResponse>>(Result.Loading)
    val getAddresses = _getAddresses.asStateFlow()

    private val _removeAddress = MutableStateFlow<Result<AddressesResponse>>(Result.Loading)
    val removeAddress = _getAddresses.asStateFlow()

    private val _isDefaultAddress = MutableStateFlow<Result<AddAddressResponse>>(Result.Loading)
    val isDefaultAddress = _isDefaultAddress.asStateFlow()


    fun getAddresses(auth: String, guestToken: String? = null) {
        viewModelScope.launch {
            handleRequest(
                request = {
                    getAddressUseCase.getAddresses(
                        auth = auth,
                        guestToken = guestToken
                    )
                },
                state = _getAddresses
            )
        }
    }

    fun removeAddress(auth: String, addressId: String, guestToken: String? = null) {
        viewModelScope.launch {
            handleRequest(
                request = {
                    removeAddressUseCase.removeAddress(
                        auth = auth,
                        addressId = addressId,
                        guestToken = guestToken
                    )
                },
                state = _removeAddress
            )
        }

    }

    fun isDefaultAddress(
        auth: String, addressId: String,
        guestToken: String? = null,
        isDefaultRequest: Int
    ) {
        viewModelScope.launch {
            handleRequest(
                request = {
                    isDefaultAddressUseCase.isDefaultAddress(
                        auth = auth,
                        addressId = addressId,
                        guestToken = guestToken,
                        isDefaultRequest = isDefaultRequest
                    )
                },
                state = _isDefaultAddress
            )
        }
    }
}