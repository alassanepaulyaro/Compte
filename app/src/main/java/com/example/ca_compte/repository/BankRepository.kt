package com.example.ca_compte.repository

import com.example.ca_compte.data.model.Bank
import retrofit2.Response

interface BankRepository {
    suspend fun getBankData() : Response<List<Bank>>
}