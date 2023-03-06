package com.example.ca_compte.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.data.model.BankItem
import com.example.ca_compte.useCase.BankUseCase
import com.example.ca_compte.utils.NetworkUtil.Companion.hasInternetConnection
import com.example.ca_compte.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val bankUseCase : BankUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _bankData = MutableLiveData<Resource<List<BankItem>>>()
    var bankData : LiveData<Resource<List<BankItem>>> = _bankData

    fun getBankData() {
        _bankData.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val response = bankUseCase.invoke()
                if (hasInternetConnection(context)) {
                    _bankData.value =  handleBankDataResponse(response)
                }
                else {
                    _bankData.value = Resource.Error("No Internet Connection")
                }
            } catch (ex: Exception) {
                when(ex){
                    is IOException -> _bankData.value = Resource.Error("Network Failure")
                    else -> _bankData.value = Resource.Error("Conversion Error")
                }
            }
        }
    }

    private fun handleBankDataResponse(response: Response<List<Bank>>): Resource<List<BankItem>>? {
        return when {
            response.message().toString().contains("timeout") -> {
                Resource.Error("Timeout")
            }
            response.code() == 402 -> {
                Resource.Error("API Key Limited.")
            }
            response.code() == 404 -> {
                Resource.Error("File not found.")
            }
            response.body().isNullOrEmpty() -> {
                return Resource.Error("Bank List not found.")
            }
            response.isSuccessful -> {
                val bankList = response.body()
                Resource.Success(getListBankToDisplay(bankList!!))
            }
            else -> {
                Resource.Error(response.message())
            }
        }
    }

    private fun getListBankToDisplay(banks: List<Bank>) : List<BankItem> {
        var listItemsBankUi = mutableListOf <BankItem>()
        listItemsBankUi.clear()
        listItemsBankUi.add(BankItem.Title(1, "Credit Agricole"))
        listItemsBankUi.addAll(getListBankCAV2(banks))
        listItemsBankUi.add(BankItem.Title(2, "Autres Banques"))
        listItemsBankUi.addAll(getListBankOtherV2(banks))
        return listItemsBankUi
    }


    /**
     * get list bank CA
     */
    fun getListBankCAV2(banks: List<Bank>): List<BankItem.BankUi>  {
        return banks.filter { it.isCA == 1 }
            .sortedBy { it.name }
            .map { BankItem.BankUi(it.name, it.accounts,  false) }
    }

    /**
     * get list bank other
     */
    fun getListBankOtherV2(banks: List<Bank>): List<BankItem.BankUi>  {
        return banks.filter { it.isCA == 0 }
            .sortedBy { it.name }
            .map { BankItem.BankUi(it.name, it.accounts,  false) }
    }
}