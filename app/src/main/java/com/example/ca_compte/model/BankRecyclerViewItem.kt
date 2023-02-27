package com.example.ca_compte.model

import com.example.ca_compte.data.Account

/**
 * Item Bank RecyclerView
 */
sealed class BankRecyclerViewItem {

    class Title(
        val id: Int,
        val title: String
    ) : BankRecyclerViewItem()

    class BankCA(
    val accounts: List<Account>,
    val isCA: Int,
    val name: String
    ): BankRecyclerViewItem()

}
