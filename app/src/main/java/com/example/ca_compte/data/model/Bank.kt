package com.example.ca_compte.data.model

data class Bank(
    val accounts: List<Account>,
    val isCA: Int,
    val name: String
)