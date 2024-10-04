package com.ag.projects.shamsstorecompose.presentation.screen.qa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.qa.policy.PolicyDataResponse
import com.ag.projects.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.ag.projects.shamsstorecompose.utils.Result
import com.google.gson.JsonParseException
import kotlinx.coroutines.launch
import java.io.IOException

@HiltViewModel
class AppInfoViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    init {
        getPrivacyPolicyData()
    }

    private val _privacyPolicyState = MutableStateFlow<Result<PolicyDataResponse>>(Result.Loading)
    val privacyPolicyState = _privacyPolicyState.asStateFlow()

     private fun getPrivacyPolicyData() {
        viewModelScope.launch {
            try {
                val privacyPolicyResponse = getProductsUseCase.getPrivacyPolicy()
                _privacyPolicyState.emit(Result.Success(privacyPolicyResponse))
            } catch (networkException: IOException) {
                _privacyPolicyState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _privacyPolicyState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _privacyPolicyState.emit(Result.Error("Unexpected error", e))
            }
        }
    }
}