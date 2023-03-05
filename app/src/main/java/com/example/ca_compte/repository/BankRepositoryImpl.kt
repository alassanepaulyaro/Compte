package com.example.ca_compte.repository

import androidx.annotation.WorkerThread
import com.example.ca_compte.data.BankAPI
import com.example.ca_compte.data.model.Bank
import retrofit2.Response
import javax.inject.Inject

class BankRepositoryImpl@Inject constructor(private val bankAPI: BankAPI) : BankRepository {
    @WorkerThread
    override suspend fun getBankData(): Response<List<Bank>> {
        return bankAPI.getBankData()
    }
}