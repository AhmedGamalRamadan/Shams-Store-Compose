package com.ag.projects.domain.usecase.app_info

import com.ag.projects.domain.model.qa.about.AboutResponse
import com.ag.projects.domain.model.qa.contact_us.ContactUsResponse
import com.ag.projects.domain.model.qa.faq.FAQResponse
import com.ag.projects.domain.model.qa.policy.PolicyDataResponse
import com.ag.projects.domain.model.qa.tarms_conditon.TermsAndConditionResponse
import com.ag.projects.domain.repository.ProductsRepository

class GetAppInfoUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getPrivacyPolicy(): PolicyDataResponse = productsRepository.getPrivacyPolicy()

    suspend fun getTermsAndCondition(): TermsAndConditionResponse =
        productsRepository.getTermsAndCondition()

    suspend fun getAboutUsData(): AboutResponse = productsRepository.getAboutUsData()

    suspend fun getFAQData(): FAQResponse = productsRepository.getFAQData()

    suspend fun getContactUs(): ContactUsResponse = productsRepository.getContactUs()
}