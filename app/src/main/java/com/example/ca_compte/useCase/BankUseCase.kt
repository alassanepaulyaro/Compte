package com.example.ca_compte.useCase

import com.example.ca_compte.repository.BankRepositoryImpl
import javax.inject.Inject

class BankUseCase @Inject constructor(private val repository: BankRepositoryImpl) {
    suspend operator fun invoke() = repository.getBankData()
}