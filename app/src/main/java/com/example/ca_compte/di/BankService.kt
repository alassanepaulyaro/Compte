package com.example.ca_compte.di

import com.example.ca_compte.data.model.Bank
import retrofit2.Response
import retrofit2.http.GET

interface BankService {
    @GET("banks.json")
    suspend fun getBanks() : Response<List<Bank>>
}