package com.example.ca_compte.data.model

/**
 * Bank Item used in the UI
 */
sealed class BankItem {
    class Title(
        val id: Int,
        val title: String
    ) : BankItem()

   class BankUi(
       val name: String,
       val accounts: List<Account>,
       var isExpanded: Boolean? = false
   ) : BankItem()
}