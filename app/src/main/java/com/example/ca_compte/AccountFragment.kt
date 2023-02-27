package com.example.ca_compte

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ca_compte.data.Bank
import com.example.ca_compte.data.UserData
import com.example.ca_compte.model.BankRecyclerViewAdapter
import com.example.ca_compte.model.BankRecyclerViewItem
import com.google.gson.Gson
import java.io.IOException
import com.example.ca_compte.databinding.FragmentAccountBinding
/**
 * AccountFragment
 */
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    var listItems = mutableListOf <BankRecyclerViewItem>()
    private val bankRecyclerViewAdapter = BankRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listItems.clear()
        listItems.add(BankRecyclerViewItem.Title(1, "Credit Agricole"))
        listItems.addAll(getListBankCAV1(getUserDataBank(requireContext())))
        listItems.add(BankRecyclerViewItem.Title(2, "Credit Autre Bank"))
        listItems.addAll(getListBankOtherV1(getUserDataBank(requireContext())))


        binding.recyclerviewAccount.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bankRecyclerViewAdapter
        }
        bankRecyclerViewAdapter.items = listItems
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

        // val gson = Gson()
        val userData = Gson().fromJson(jsonString, UserData::class.java)
        return userData.banks
    }

    fun getListBankCA(banks: List<Bank>): List<Bank>  {
        return banks.filter { it.isCA == 1 }
            .sortedBy { it.name }
    }

    fun getListBankAutre(banks: List<Bank>): List<Bank> {
        return banks.filter { it.isCA == 0 }
            .sortedBy { it.name }
    }

    /**
     * get list bank CA
     */
    fun getListBankCAV1(banks: List<Bank>): List<BankRecyclerViewItem.BankCA>  {
        return banks.filter { it.isCA == 1 }
            .sortedBy { it.name }
            .map { BankRecyclerViewItem.BankCA(it.accounts, it.isCA, it.name) }
    }

    /**
     * get list bank other
     */
    fun getListBankOtherV1(banks: List<Bank>): List<BankRecyclerViewItem.BankCA>  {
        return banks.filter { it.isCA == 0 }
            .sortedBy { it.name }
            .map { BankRecyclerViewItem.BankCA(it.accounts, it.isCA, it.name) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}