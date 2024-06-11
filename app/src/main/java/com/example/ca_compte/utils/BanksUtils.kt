package com.example.ca_compte.utils

import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.data.model.BankItem

class BanksUtils {
    companion object {
        /**
         * get list bank CA
         */
        fun getCABanks(banks: List<Bank>): List<BankItem.BankUi> {
            return banks.filter { it.isCA == 1 }
                .sortedBy { it.name }
                .map { BankItem.BankUi(it.name, it.accounts, false) }
        }

        /**
         * get list bank other
         */
        fun getOtherBanks(banks: List<Bank>): List<BankItem.BankUi> {
            return banks.filter { it.isCA == 0 }
                .sortedBy { it.name }
                .map { BankItem.BankUi(it.name, it.accounts, false) }
        }

        /**
         * return data to display
         */
        fun getBankItemsToDisplay(banks: List<Bank>): List<BankItem> {
            var listItemsBankUi = mutableListOf<BankItem>()
            listItemsBankUi.clear()
            listItemsBankUi.add(BankItem.Title(1, "Credit Agricole"))
            listItemsBankUi.addAll(getCABanks(banks))
            listItemsBankUi.add(BankItem.Title(2, "Autres Banques"))
            listItemsBankUi.addAll(getOtherBanks(banks))
            return listItemsBankUi
        }
    }
}