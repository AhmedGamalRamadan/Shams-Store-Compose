package com.ag.projects.shamsstorecompose.presentation.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.domain.model.auth.verify.VerifyResponse
import com.ag.projects.domain.usecase.auth.register.RegisterUseCase
import com.google.gson.JsonParseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import com.ag.projects.shamsstorecompose.utils.Result
import javax.inject.Inject

@HiltViewModel
class RegisterUserNameViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) :ViewModel(){


    private val _register = MutableStateFlow<Result<VerifyResponse>>(Result.Loading)
    val register = _register.asStateFlow()



    fun register(registerRequest: AuthenticationRequest) {
        viewModelScope.launch {
            try {
                _register.emit(Result.Success(registerUseCase.register(registerRequest)))
            } catch (networkException: IOException) {
                _register.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _register.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _register.emit(Result.Error("Unexpected error", e))
            }
        }
    }
}