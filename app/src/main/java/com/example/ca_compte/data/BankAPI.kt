package com.example.ca_compte.data

import com.example.ca_compte.data.model.Bank
import retrofit2.Response
import retrofit2.http.GET

interface BankAPI {
    @GET("accounts")
    suspend fun getBankData() : Response<List<Bank>>
}