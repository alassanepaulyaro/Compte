package com.example.ca_compte.utils

import com.example.ca_compte.data.model.Account
import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.data.model.BankItem
import com.example.ca_compte.data.model.Operation

class DataMock {
    companion object {
        val mockBanks = listOf(
            Bank(
                accounts = listOf(
                    Account(
                        balance = 425.84,
                        contract_number = "32216549871",
                        holder = "Corinne Martin",
                        id = "3982938",
                        label = "Compte de dépôt",
                        operations = listOf(
                            Operation(
                                amount = "-1,99",
                                category = "costs",
                                date = "1644870724",
                                id = "2",
                                title = "Tenue de compte"
                            ),
                            Operation(
                                amount = "-45,99",
                                category = "leisure",
                                date = "1644870724",
                                id = "2",
                                title = "Prélèvement Orange"
                            )
                        ),
                        order = 1,
                        product_code = "00004",
                        role = 1
                    )
                ),
                isCA = 1,
                name = "CA Centre-Est"
            ),
            Bank(
                accounts = listOf(
                    Account(
                        balance = 2031.84,
                        contract_number = "32216549871",
                        holder = "Corinne Martin",
                        id = "151515151151",
                        label = "Compte de dépôt",
                        operations = listOf(
                            Operation(
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644870724",
                                id = "2",
                                title = "Prélèvement Netflix"
                            ),
                            Operation(
                                amount = "-95,99",
                                category = "online",
                                date = "1644611558",
                                id = "4",
                                title = "CB Amazon"
                            )
                        ),
                        order = 1,
                        product_code = "00004",
                        role = 1
                    ),
                    Account(
                        balance = 843.15,
                        contract_number = "09320939231",
                        holder = "M. et Mme Martin",
                        id = "9892736780987654",
                        label = "Compte joint",
                        operations = listOf(
                            Operation(
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644784369",
                                id = "2",
                                title = "Prélèvement Netflix"
                            ),
                            Operation(
                                amount = "-750,00",
                                category = "housing",
                                date = "1644179569",
                                id = "3",
                                title = "Prélèvement Century 21"
                            )
                        ),
                        order = 2,
                        product_code = "00007",
                        role = 2
                    ),
                    Account(
                        balance = 209.39,
                        contract_number = "29389382872",
                        holder = "Thaïs Martin",
                        id = "2354657678098765",
                        label = "Compte Mozaïc",
                        operations = listOf(
                            Operation(
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644438769",
                                id = "2",
                                title = "Orange"
                            )
                        ),
                        order = 3,
                        product_code = "00007",
                        role = 6
                    )
                ),
                isCA = 1,
                name = "CA Languedoc"
            ),
            Bank(
                accounts = listOf(
                    Account(
                        balance = 675.04,
                        contract_number = "32216549871",
                        holder = "Jean Martin",
                        id = "3982997777",
                        label = "Compte Chèques",
                        operations = listOf(
                            Operation(
                                amount = "-1331,44",
                                category = "costs",
                                date = "1644179569",
                                id = "2",
                                title = "Prêt immo"
                            ),
                            Operation(
                                amount = "-53,20",
                                category = "food",
                                date = "1644784369",
                                id = "2",
                                title = "CB La Vie Claire"
                            ),
                            Operation(
                                amount = "-10,00",
                                category = "leisure",
                                date = "1644611558",
                                id = "3",
                                title = "Prélèvement Spotify"
                            ),
                            Operation(
                                amount = "-53,00",
                                category = "trip",
                                date = "1644870724",
                                id = "4",
                                title = "CB Billets SNCF"
                            )
                        ),
                        order = 1,
                        product_code = "00004",
                        role = 1
                    )
                ),
                isCA = 0,
                name = "Banque Pop"
            ),
            Bank(
                accounts = listOf(
                    Account(
                        balance = 2031.84,
                        contract_number = "32216549871",
                        holder = "Corinne Martin",
                        id = "151515151151",
                        label = "Compte de dépôt",
                        operations = listOf(
                            Operation(
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644870724",
                                id = "2",
                                title = "Prélèvement Netflix"
                            ),
                            Operation(
                                amount = "-95,99",
                                category = "online",
                                date = "1644611558",
                                id = "4",
                                title = "CB Amazon"
                            )
                        ),
                        order = 1,
                        product_code = "00004",
                        role = 1
                    ),
                ),
                isCA = 0,
                name = "Boursorama"
            )
        )


        val expectedBankItems = listOf(
            BankItem.Title(1, "Credit Agricole"),
            BankItem.BankUi(
                "CA Centre-Est", listOf(
                    Account(
                        balance = 425.84,
                        contract_number = "32216549871",
                        holder = "Corinne Martin",
                        id = "3982938",
                        label = "Compte de dépôt",
                        operations = listOf(
                            Operation(
                                amount = "-1,99",
                                category = "costs",
                                date = "1644870724",
                                id = "2",
                                title = "Tenue de compte"
                            ),
                            Operation(
                                amount = "-45,99",
                                category = "leisure",
                                date = "1644870724",
                                id = "2",
                                title = "Prélèvement Orange"
                            )
                        ),
                        order = 1,
                        product_code = "00004",
                        role = 1
                    )
                ), false
            ),
            BankItem.BankUi(
                "CA Languedoc", listOf(
                    Account(
                        balance = 2031.84,
                        contract_number = "32216549871",
                        holder = "Corinne Martin",
                        id = "151515151151",
                        label = "Compte de dépôt",
                        operations = listOf(
                            Operation(
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644870724",
                                id = "2",
                                title = "Prélèvement Netflix"
                            ),
                            Operation(
                                amount = "-95,99",
                                category = "online",
                                date = "1644611558",
                                id = "4",
                                title = "CB Amazon"
                            )
                        ),
                        order = 1,
                        product_code = "00004",
                        role = 1
                    ),
                    Account(
                        balance = 843.15,
                        contract_number = "09320939231",
                        holder = "M. et Mme Martin",
                        id = "9892736780987654",
                        label = "Compte joint",
                        operations = listOf(
                            Operation(
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644784369",
                                id = "2",
                                title = "Prélèvement Netflix"
                            ),
                            Operation(
                                amount = "-750,00",
                                category = "housing",
                                date = "1644179569",
                                id = "3",
                                title = "Prélèvement Century 21"
                            )
                        ),
                        order = 2,
                        product_code = "00007",
                        role = 2
                    ),
                    Account(
                        balance = 209.39,
                        contract_number = "29389382872",
                        holder = "Thaïs Martin",
                        id = "2354657678098765",
                        label = "Compte Mozaïc",
                        operations = listOf(
                            Operation(
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644438769",
                                id = "2",
                                title = "Orange"
                            )
                        ),
                        order = 3,
                        product_code = "00007",
                        role = 6
                    )
                ), false
            ),
            BankItem.Title(2, "Autres Banques"),
            BankItem.BankUi(
                "Banque Pop", listOf(
                    Account(
                        balance = 675.04,
                        contract_number = "32216549871",
                        holder = "Jean Martin",
                        id = "3982997777",
                        label = "Compte Chèques",
                        operations = listOf(
                            Operation(
                                amount = "-1331,44",
                                category = "costs",
                                date = "1644179569",
                                id = "2",
                                title = "Prêt immo"
                            ),
                            Operation(
                                amount = "-53,20",
                                category = "food",
                                date = "1644784369",
                                id = "2",
                                title = "CB La Vie Claire"
                            ),
                            Operation(
                                amount = "-10,00",
                                category = "leisure",
                                date = "1644611558",
                                id = "3",
                                title = "Prélèvement Spotify"
                            ),
                            Operation(
                                amount = "-53,00",
                                category = "trip",
                                date = "1644870724",
                                id = "4",
                                title = "CB Billets SNCF"
                            )
                        ),
                        order = 1,
                        product_code = "00004",
                        role = 1
                    )
                ), false
            ),
            BankItem.BankUi(
                "Boursorama", listOf(
                    Account(
                        balance = 2031.84,
                        contract_number = "32216549871",
                        holder = "Corinne Martin",
                        id = "151515151151",
                        label = "Compte de dépôt",
                        operations = listOf(
                            Operation(
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644870724",
                                id = "2",
                                title = "Prélèvement Netflix"
                            ),
                            Operation(
                                amount = "-95,99",
                                category = "online",
                                date = "1644611558",
                                id = "4",
                                title = "CB Amazon"
                            )
                        ),
                        order = 1,
                        product_code = "00004",
                        role = 1
                    ),
                ), false
            )
        )
    }
}