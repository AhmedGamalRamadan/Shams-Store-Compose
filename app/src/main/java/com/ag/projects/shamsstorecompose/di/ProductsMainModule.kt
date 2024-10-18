package com.ag.projects.shamsstorecompose.di

import com.ag.projects.data.remote.ProductsAPIServices
import com.ag.projects.data.repository.ProductsRepositoryImpl
import com.ag.projects.domain.repository.ProductsRepository
import com.ag.projects.domain.usecase.auth.login.LoginUseCase
import com.ag.projects.domain.usecase.auth.register.RegisterUseCase
import com.ag.projects.domain.usecase.auth.verify.VerifyUSeCase
import com.ag.projects.domain.usecase.products.GetProductsUseCase
import com.ag.projects.domain.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsMainModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.MILLISECONDS)
            .connectTimeout(20, TimeUnit.MILLISECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiServices(okHttpClient: OkHttpClient): ProductsAPIServices {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("ProductModel-Type", "application/json")
                    .addHeader("os", "android")
                    .addHeader("type", "android")
                    .addHeader("Accept-Language", "en")
                    .addHeader("lang", "en")
                    .build()
                val mChain = chain.proceed(newRequest)

                mChain
            }.build()
        val baseUrl = Constants.BASE_URL
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductsAPIServices::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(productsApiServices: ProductsAPIServices): ProductsRepository {
        return ProductsRepositoryImpl(productsApiServices)
    }

    @Provides
    @Singleton
    fun provideProductsUseCase(productsRepository: ProductsRepository) =
        GetProductsUseCase(productsRepository)

    /*
    Auth
     */

    @Provides
    @Singleton
    fun provideLoginUseCase(productsRepository: ProductsRepository) =
        LoginUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideRegisterUseCase(productsRepository: ProductsRepository) =
        RegisterUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideVerifyOTPUseCase(productsRepository: ProductsRepository) =
        VerifyUSeCase(productsRepository)
}