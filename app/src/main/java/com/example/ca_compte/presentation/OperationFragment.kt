package com.example.ca_compte.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ca_compte.adapters.OperationAdapter
import com.example.ca_compte.databinding.FragmentOperationBinding

/**
 * OperationFragment
 */
class OperationFragment : Fragment() {

    private var _binding: FragmentOperationBinding? = null
    private val binding get() = _binding!!
    private val args : OperationFragmentArgs by navArgs()
    private lateinit var operationAdapter: OperationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOperationBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val account = args.accountData
        Log.d("OperationFragment", "onViewCreated :  "+ account.holder)
        operationAdapter = OperationAdapter(account.operations)
        binding.apply {
            operationAmountTitle.text = account.label
            operationAmountHolder.text =  account.balance.toString() + " \u20ac"

            recyclerviewOperation.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter=operationAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}