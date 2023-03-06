package com.example.ca_compte.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.ca_compte.R
import com.example.ca_compte.adapters.BankAdapter
import com.example.ca_compte.data.model.Account
import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.data.model.UserData
import com.example.ca_compte.databinding.FragmentAccountBinding
import com.example.ca_compte.data.model.BankItem
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

/**
 * AccountFragment
 */
@AndroidEntryPoint
class AccountFragment : Fragment(), UserClickListener {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

   var listItemsBankUi = mutableListOf <BankItem>()

    private val accountViewModel : AccountViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listItemsBankUi.clear()
        listItemsBankUi.add(BankItem.Title(1, "Credit Agricole"))
        listItemsBankUi.addAll(getListBankCAV2(getUserDataBank(requireContext())))
        listItemsBankUi.add(BankItem.Title(2, "Autres Banques"))
        listItemsBankUi.addAll(getListBankOtherV2(getUserDataBank(requireContext())))

        setUpListOfAdapter(listItemsBankUi)

        /*accountViewModel.bankData.observe(viewLifecycleOwner) {
            result ->
            when(result) {
                is Resource.Success -> {
                    Log.e("AccountFragment Status: ", " Status.SUCCESS " + result.data.toString())
                    result.data?.let { setUpListOfAdapter(it) }
                }
                is  Resource.Error -> {
                    Log.e("AccountFragment Status: ", "Status.ERROR:  " +result.message)
                }
                is Resource.Loading -> {
                    Log.e("AccountFragment Status: ", "Status.LOADING "+ result.message)
                }
            }
        }
        accountViewModel.getBankData()*/

    }

    /**
     * Setup recyclerView list of items
     */
    private fun setUpListOfAdapter(items : List<BankItem>){
        val bankAdapter = BankAdapter(items, this)
        binding.recyclerviewAccount.apply {
            setHasFixedSize(true)
            adapter = bankAdapter
        }
    }

    /**
     * Get data form Json File
     */
    private fun getUserDataBank(context: Context): List<Bank> {
        lateinit var jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.banks)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("MainActivity user", ioException.toString())
        }

        val userData = Gson().fromJson(jsonString, UserData::class.java)
        return userData.banks
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun mClick(item: Account) {
        val action = AccountFragmentDirections.actionAccountFragmentToOperationFragment(item)
        view?.let { Navigation.findNavController(it).navigate(action) }
    }
}