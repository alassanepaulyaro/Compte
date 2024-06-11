package com.example.ca_compte.repository

import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.utils.Resource

interface BankRepository {
    suspend fun getBanks() : Resource<List<Bank>>
}