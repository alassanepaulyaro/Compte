package com.example.ca_compte.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ca_compte.presentation.UserClickListener
import com.example.ca_compte.data.model.Account
import com.example.ca_compte.databinding.BankChildItemBinding

/**
 * Account Adapter
 */
class AccountAdapter (val data: List<Account>?, val userClickListener: UserClickListener) :
    RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {
    private var items: List<Account> ? = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BankChildItemBinding.inflate(inflater,parent,false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        items?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount() = items?.size?: 0

    inner class AccountViewHolder(private val binding: BankChildItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(account: Account) {
            binding.apply {
                accountChildTitle.text = account.holder
                accountChildAmount.text= account.balance.toString() +" \u20ac"
                itemView.setOnClickListener {
                    userClickListener.mClick(account)
                }
            }
        }
    }
}