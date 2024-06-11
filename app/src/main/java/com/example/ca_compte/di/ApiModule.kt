package com.example.ca_compte.di

import com.example.ca_compte.repository.BankRepositoryImpl
import com.example.ca_compte.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class ApiModule {

    @Singleton
    @Provides
    fun providerBankApi(): BankService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BankService::class.java)

    @Singleton
    @Provides
    fun providerBankRepository(bankService: BankService) = BankRepositoryImpl(bankService)
    open var BASE_URL: String = "https://test.api.com/"
}