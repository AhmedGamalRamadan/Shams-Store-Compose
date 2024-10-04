package com.ag.projects.domain.repository

import com.ag.projects.domain.model.brand.CategoriesResponse
import com.ag.projects.domain.model.home.ProductsResponse
import com.ag.projects.domain.model.qa.about.AboutResponse
import com.ag.projects.domain.model.qa.contact_us.ContactUsResponse
import com.ag.projects.domain.model.qa.faq.FAQResponse
import com.ag.projects.domain.model.qa.policy.PolicyDataResponse
import com.ag.projects.domain.model.qa.tarms_conditon.TermsAndConditionResponse

interface ProductsRepository {

    suspend fun getAllProducts(): ProductsResponse

    suspend fun getAllCategories(): CategoriesResponse

    suspend fun getAllBrands(): CategoriesResponse

    suspend fun getPrivacyPolicy(): PolicyDataResponse

    suspend fun getTermsAndCondition(): TermsAndConditionResponse

    suspend fun getAboutUsData(): AboutResponse

    suspend fun getFAQData(): FAQResponse

    suspend fun getContactUs(): ContactUsResponse
}