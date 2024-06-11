package com.example.ca_compte.repository

import androidx.annotation.WorkerThread
import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.di.BankService
import com.example.ca_compte.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class BankRepositoryImpl@Inject constructor(val bankService: BankService) : BankRepository {
    @WorkerThread
    override suspend fun getBanks(): Resource<List<Bank>> {
        return try {
            val response: Response<List<Bank>> = bankService.getBanks()
            if (response.isSuccessful) {
                Resource.Success(response.body() ?: emptyList())
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}