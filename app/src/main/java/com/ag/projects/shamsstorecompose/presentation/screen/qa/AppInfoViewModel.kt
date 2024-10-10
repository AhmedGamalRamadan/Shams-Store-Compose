package com.ag.projects.shamsstorecompose.presentation.screen.qa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.domain.model.qa.about.AboutResponse
import com.ag.projects.domain.model.qa.contact_us.ContactUsResponse
import com.ag.projects.domain.model.qa.faq.FAQResponse
import com.ag.projects.domain.model.qa.policy.PolicyDataResponse
import com.ag.projects.domain.model.qa.tarms_conditon.TermsAndConditionResponse
import com.ag.projects.domain.usecase.products.GetProductsUseCase
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
        getFAqData()
        getTermsAndConditionData()
    }

    private val _privacyPolicyState = MutableStateFlow<Result<PolicyDataResponse>>(Result.Loading)
    val privacyPolicyState = _privacyPolicyState.asStateFlow()

    private val _termsAndConditionState =
        MutableStateFlow<Result<TermsAndConditionResponse>>(Result.Loading)
    val termsAndConditionState = _termsAndConditionState.asStateFlow()

    private val _aboutUsState = MutableStateFlow<Result<AboutResponse>>(Result.Loading)
    val aboutUsState = _aboutUsState.asStateFlow()

    private val _faqState = MutableStateFlow<Result<FAQResponse>>(Result.Loading)
    val faqState = _faqState.asStateFlow()

    private val _contactUsState = MutableStateFlow<Result<ContactUsResponse>>(Result.Loading)
    val contactUsState = _contactUsState.asStateFlow()

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

    private fun getTermsAndConditionData() {
        viewModelScope.launch {
            try {
                val termsAndConditionResponse = getProductsUseCase.getTermsAndCondition()
                _termsAndConditionState.emit(Result.Success(termsAndConditionResponse))
            } catch (networkException: IOException) {
                _termsAndConditionState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _termsAndConditionState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _termsAndConditionState.emit(Result.Error("Unexpected error", e))
            }
        }
    }

    private fun getAboutUsData() {
        viewModelScope.launch {
            try {
                val aboutUsResponse = getProductsUseCase.getAboutUsData()
                _aboutUsState.emit(Result.Success(aboutUsResponse))
            } catch (networkException: IOException) {
                _aboutUsState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _aboutUsState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _aboutUsState.emit(Result.Error("Unexpected error", e))
            }
        }

    }

    private fun getFAqData() {
        viewModelScope.launch {
            try {
                val faqResponse = getProductsUseCase.getFAQData()
                _faqState.emit(Result.Success(faqResponse))
            } catch (networkException: IOException) {
                _faqState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _faqState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _faqState.emit(Result.Error("Unexpected error", e))
            }
        }

    }

    private fun getContactUSData() {
        viewModelScope.launch {
            try {
                val contactUsResponse = getProductsUseCase.getContactUs()
                _contactUsState.emit(Result.Success(contactUsResponse))
            } catch (networkException: IOException) {
                _contactUsState.emit(Result.Error("Network error", networkException))
            } catch (jsonException: JsonParseException) {
                _contactUsState.emit(Result.Error("Data parsing error", jsonException))
            } catch (e: Exception) {
                _contactUsState.emit(Result.Error("Unexpected error", e))
            }
        }
    }
}