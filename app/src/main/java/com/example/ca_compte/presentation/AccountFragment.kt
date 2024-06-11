package com.example.ca_compte.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.ca_compte.adapters.BankAdapter
import com.example.ca_compte.data.model.Account
import com.example.ca_compte.data.model.BankItem
import com.example.ca_compte.databinding.FragmentAccountBinding
import com.example.ca_compte.utils.BanksUtils.Companion.getBankItemsToDisplay
import com.example.ca_compte.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * AccountFragment
 */
@AndroidEntryPoint
class AccountFragment : Fragment(), UserClickListener {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

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

        accountViewModel.bankData.observe(viewLifecycleOwner) {
            result ->
            when(result) {
                is Resource.Success -> {
                    Log.e("AccountFragment Status: ", " Status.SUCCESS " + result.data.toString())
                    Log.e("AccountFragment Status: ", " getBankItemsToDisplay " + getBankItemsToDisplay(result.data!!).toString() )


                    result.data?.let { setUpListOfAdapter(binding,getBankItemsToDisplay(it)) }
                }
                is  Resource.Error -> {
                    Log.e("AccountFragment Status: ", "Status.ERROR:  " +result.message)
                }
                is Resource.Loading -> {
                    Log.e("AccountFragment Status: ", "Status.LOADING "+ result.message)
                }
            }
        }
        accountViewModel.getBankData()
    }

    /**
     * Setup recyclerView list of items
     */
    private fun setUpListOfAdapter(binding: FragmentAccountBinding, items : List<BankItem>){
        val bankAdapter = BankAdapter(items, this)
        binding.recyclerviewAccount.apply {
            setHasFixedSize(true)
            adapter = bankAdapter
        }
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