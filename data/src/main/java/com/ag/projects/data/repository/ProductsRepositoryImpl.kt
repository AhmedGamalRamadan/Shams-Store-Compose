package com.ag.projects.data.repository

import com.ag.projects.data.remote.ProductsAPIServices
import com.ag.projects.domain.model.brand.CategoriesResponse
import com.ag.projects.domain.model.home.ProductsResponse
import com.ag.projects.domain.model.qa.about.AboutResponse
import com.ag.projects.domain.model.qa.contact_us.ContactUsResponse
import com.ag.projects.domain.model.qa.faq.FAQResponse
import com.ag.projects.domain.model.qa.policy.PolicyDataResponse
import com.ag.projects.domain.model.qa.tarms_conditon.TermsAndConditionResponse
import com.ag.projects.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val productsAPIServices: ProductsAPIServices
) : ProductsRepository {

    override suspend fun getAllProducts(): ProductsResponse = productsAPIServices.getAllProducts()

    override suspend fun getAllCategories(): CategoriesResponse =
        productsAPIServices.getAllCategories()

    override suspend fun getAllBrands(): CategoriesResponse =
        productsAPIServices.getAllBrands()

    override suspend fun getPrivacyPolicy(): PolicyDataResponse =
        productsAPIServices.getPrivacyPolicy()

    override suspend fun getTermsAndCondition(): TermsAndConditionResponse =
        productsAPIServices.getTermsAndCondition()

    override suspend fun getAboutUsData(): AboutResponse =
        productsAPIServices.getAboutUsData()

    override suspend fun getFAQData(): FAQResponse =
        productsAPIServices.getFAQData()

    override suspend fun getContactUs(): ContactUsResponse =
        productsAPIServices.getContactUs()

}