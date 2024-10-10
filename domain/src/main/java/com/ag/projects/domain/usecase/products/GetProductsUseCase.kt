package com.ag.projects.domain.usecase.products

import com.ag.projects.domain.model.products.home.ProductsResponse
import com.ag.projects.domain.model.qa.about.AboutResponse
import com.ag.projects.domain.model.qa.contact_us.ContactUsResponse
import com.ag.projects.domain.model.qa.faq.FAQResponse
import com.ag.projects.domain.model.qa.policy.PolicyDataResponse
import com.ag.projects.domain.model.qa.tarms_conditon.TermsAndConditionResponse
import com.ag.projects.domain.repository.ProductsRepository

class GetProductsUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getAllProductsRemote(): ProductsResponse = productsRepository.getAllProducts()

    suspend fun getAllCategories() = productsRepository.getAllCategories()

    suspend fun getAllBrands() = productsRepository.getAllBrands()

    suspend fun getPrivacyPolicy(): PolicyDataResponse = productsRepository.getPrivacyPolicy()

    suspend fun getTermsAndCondition(): TermsAndConditionResponse =
        productsRepository.getTermsAndCondition()

    suspend fun getAboutUsData(): AboutResponse = productsRepository.getAboutUsData()

    suspend fun getFAQData(): FAQResponse = productsRepository.getFAQData()

    suspend fun getContactUs(): ContactUsResponse = productsRepository.getContactUs()
}