package com.ag.projects.shamsstorecompose.di

import com.ag.projects.data.remote.ProductsAPIServices
import com.ag.projects.data.repository.ProductsRepositoryImpl
import com.ag.projects.domain.repository.ProductsRepository
import com.ag.projects.domain.usecase.addresses.create.CreateAddressUseCase
import com.ag.projects.domain.usecase.addresses.get.GetAddressesUseCase
import com.ag.projects.domain.usecase.addresses.is_default.IsDefaultAddressUseCase
import com.ag.projects.domain.usecase.addresses.remove.RemoveAddressUseCase
import com.ag.projects.domain.usecase.addresses.update.UpdateAddressUseCase
import com.ag.projects.domain.usecase.app_info.GetAppInfoUseCase
import com.ag.projects.domain.usecase.auth.login.LoginUseCase
import com.ag.projects.domain.usecase.auth.register.RegisterUseCase
import com.ag.projects.domain.usecase.auth.verify.VerifyUSeCase
import com.ag.projects.domain.usecase.cart.add.AddToCartUseCase
import com.ag.projects.domain.usecase.cart.delete.DeleteAllCartsUseCase
import com.ag.projects.domain.usecase.cart.delete.DeleteCartItemUseCase
import com.ag.projects.domain.usecase.cart.get.GetShoppingCartUseCase
import com.ag.projects.domain.usecase.details.GetProductDetailsUseCase
import com.ag.projects.domain.usecase.products.GetProductsUseCase
import com.ag.projects.domain.usecase.products.all_categories.GetAllCategoriesBrandsUseCase
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

    @Provides
    @Singleton
    fun provideProductDetailsUseCase(productsRepository: ProductsRepository) =
        GetProductDetailsUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideGetAppInfoUseCase(productsRepository: ProductsRepository) =
        GetAppInfoUseCase(productsRepository)

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

    /*
    Shopping cart
     */

    @Provides
    @Singleton
    fun provideGetCartsUseCase(productsRepository: ProductsRepository) =
        GetShoppingCartUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideAddToCartsUseCase(productsRepository: ProductsRepository) =
        AddToCartUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideDeleteAllCartsUseCase(productsRepository: ProductsRepository) =
        DeleteAllCartsUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideDeleteCartItemUseCase(productsRepository: ProductsRepository) =
        DeleteCartItemUseCase(productsRepository)

    /*
Addresses
 */

    @Provides
    @Singleton
    fun provideGetAddressesUseCase(productsRepository: ProductsRepository) =
        GetAddressesUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideCreateAddressesUseCase(productsRepository: ProductsRepository) =
        CreateAddressUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideRemoveAddressesUseCase(productsRepository: ProductsRepository) =
        RemoveAddressUseCase(productsRepository)


    @Provides
    @Singleton
    fun provideUpdateAddressesUseCase(productsRepository: ProductsRepository) =
        UpdateAddressUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideIsDefaultAddressesUseCase(productsRepository: ProductsRepository) =
        IsDefaultAddressUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideGetAllCategoriesBrandsUseCase(productsRepository: ProductsRepository) =
        GetAllCategoriesBrandsUseCase(productsRepository)

}