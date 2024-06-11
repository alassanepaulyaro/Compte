package com.example.ca_compte

import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.data.model.BankItem
import com.example.ca_compte.utils.BanksUtils
import com.example.ca_compte.utils.DataMock.Companion.expectedBankItems
import com.example.ca_compte.utils.DataMock.Companion.mockBanks
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class BanksUtilsTest {

    fun parametersForTestGetCABanks() = arrayOf(
        arrayOf(
            mockBanks,
            listOf(
                BankItem.BankUi("CA Centre-Est", mockBanks[0].accounts, false),
                BankItem.BankUi("CA Languedoc", mockBanks[1].accounts, false)
            )
        ),
        arrayOf(
            emptyList<Bank>(),
            emptyList<BankItem.BankUi>()
        ),
        arrayOf(
            listOf(mockBanks[0]),
            listOf(expectedBankItems[1])
        )
    )

    @Test
    @Parameters(method = "parametersForTestGetCABanks")
    fun testGetCABanks(banks: List<Bank>, expected: List<BankItem.BankUi>) {
        val result = BanksUtils.getCABanks(banks)
        assertEquals(expected.getOrNull(0)?.name, result.getOrNull(0)?.name)
        assertEquals(result?.size, result?.size)
    }

    fun parametersForTestGetOtherBanks() = arrayOf(
        arrayOf(
            mockBanks,
            listOf(
                BankItem.BankUi("Banque Pop", mockBanks[2].accounts, false),
                BankItem.BankUi("Boursorama", mockBanks[3].accounts, false)
            )
        ),
        arrayOf(
            emptyList<Bank>(),
            emptyList<BankItem.BankUi>()
        ),
        arrayOf(
            listOf(mockBanks[2]),
            listOf(expectedBankItems[4])
        )
    )

    @Test
    @Parameters(method = "parametersForTestGetOtherBanks")
    fun testGetOtherBanks(banks: List<Bank>, expected: List<BankItem.BankUi>) {
        val result = BanksUtils.getOtherBanks(banks)
        assertEquals(expected.getOrNull(0)?.name, result.getOrNull(0)?.name)
        assertEquals(result.size, result.size)
    }

    fun parametersForTestGetBankItemsToDisplay() = arrayOf(
        arrayOf(
            mockBanks,
            listOf(
                BankItem.Title(1, "Credit Agricole"),
                BankItem.BankUi("CA Centre-Est", mockBanks[0].accounts, false),
                BankItem.BankUi("CA Languedoc", mockBanks[1].accounts, false),
                BankItem.Title(2, "Autres Banques"),
                BankItem.BankUi("Banque Pop", mockBanks[2].accounts, false),
                BankItem.BankUi("Boursorama", mockBanks[3].accounts, false)
            )
        ),
        arrayOf(
            emptyList<Bank>(),
            listOf(
                BankItem.Title(1, "Credit Agricole"),
                BankItem.Title(2, "Autres Banques")
            )
        )
    )

    @Test
    @Parameters(method = "parametersForTestGetBankItemsToDisplay")
    fun testGetBankItemsToDisplay(banks: List<Bank>, expected: List<BankItem>) {
        val result = BanksUtils.getBankItemsToDisplay(banks)
        assertEquals(expected.size, result.size)

        expected.forEachIndexed { index, expectedItem ->
            val resultItem = result[index]
            when (expectedItem) {
                is BankItem.Title -> {
                    assertEquals(
                        (expectedItem as BankItem.Title).title,
                        (resultItem as BankItem.Title).title
                    )
                }

                is BankItem.BankUi -> {
                    val expectedBankUi = expectedItem as BankItem.BankUi
                    val resultBankUi = resultItem as BankItem.BankUi
                    assertEquals(expectedBankUi.name, resultBankUi.name)
                    assertEquals(expectedBankUi.accounts, resultBankUi.accounts)
                }
            }
        }
    }
}
