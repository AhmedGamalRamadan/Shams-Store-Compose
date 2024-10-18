package com.ag.projects.shamsstorecompose.presentation.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.domain.model.auth.login.LoginResponse
import com.ag.projects.domain.model.country.AllCountriesResponse
import com.ag.projects.domain.usecase.auth.login.LoginUseCase
import com.ag.projects.shamsstorecompose.utils.Result
import com.google.gson.JsonParseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(){

    private val _countries = MutableStateFlow<Result<AllCountriesResponse>>(Result.Loading)
    val countries = _countries.asStateFlow()


    private val _login = MutableStateFlow<Result<LoginResponse>>(Result.Loading)
    val login = _login.asStateFlow()

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            try {
                val countries = loginUseCase.getAllCountries()
                _countries.emit(Result.Success(countries))
            } catch (networkException: IOException) {
                _countries.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _countries.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _countries.emit(Result.Error("Unexpected error", e))
            }
        }
    }

    fun login(loginRequest: AuthenticationRequest) {
        viewModelScope.launch {
            _login.emit(Result.Loading)
            try {
                _login.emit(Result.Success(loginUseCase.login(loginRequest)))
            } catch (networkException: IOException) {
                _login.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _login.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _login.emit(Result.Error("Unexpected error", e))
            }
        }
    }
}