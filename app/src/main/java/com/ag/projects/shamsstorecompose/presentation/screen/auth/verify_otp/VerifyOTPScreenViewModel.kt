package com.ag.projects.shamsstorecompose.presentation.screen.auth.verify_otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.domain.model.auth.verify.VerifyResponse
import com.ag.projects.domain.usecase.auth.verify.VerifyUSeCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.ag.projects.shamsstorecompose.utils.Result
import com.google.gson.JsonParseException
import kotlinx.coroutines.launch
import java.io.IOException

@HiltViewModel
class VerifyOTPScreenViewModel@Inject constructor(
    private val verifyUSeCase: VerifyUSeCase,
) : ViewModel() {

    private val _verifyOTPState = MutableStateFlow<Result<VerifyResponse>>(Result.Loading)
    val verifyOTPState = _verifyOTPState.asStateFlow()

//    private val _resendCode = MutableStateFlow<Result<ResendCodeResponse>>(Result.Loading)
//    val resendCode = _resendCode.asStateFlow()


    fun verifyOTP(loginRequest: AuthenticationRequest) {
        viewModelScope.launch {
            try {
                _verifyOTPState.emit(Result.Success(verifyUSeCase.verifyOTP(loginRequest)))
            } catch (networkException: IOException) {
                _verifyOTPState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _verifyOTPState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _verifyOTPState.emit(Result.Error("Unexpected error", e))
            }
        }
    }

}