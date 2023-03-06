package com.example.ca_compte.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ca_compte.R
import com.example.ca_compte.presentation.UserClickListener
import com.example.ca_compte.data.model.Account
import com.example.ca_compte.databinding.BankHeadItemBinding
import com.example.ca_compte.databinding.BankSectionTitleBinding
import com.example.ca_compte.data.model.BankItem

/**
 * Bank Adapter
 */
class BankAdapter(val data: List<BankItem>?, val userClickListener: UserClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<BankItem> ? = data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        return when(viewType) {
            R.layout.bank_section_title -> TitleViewHolder(
                BankSectionTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.bank_head_item -> BankViewHolder(
                BankHeadItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(holder){
            is BankViewHolder ->{
                holder.bind(items?.get(position) as BankItem.BankUi)
            }
            is TitleViewHolder -> {
                holder.bind(items?.get(position) as BankItem.Title)
            }
        }
    }


    override fun getItemCount() = items?.size?: 0

    private fun onItemClicked(bankUi: BankItem.BankUi){
        bankUi?.isExpanded = !bankUi?.isExpanded!!
        notifyDataSetChanged()
    }

    inner class BankViewHolder(private val binding: BankHeadItemBinding) : RecyclerView.ViewHolder(binding.root){
        private lateinit var accountAdapter: AccountAdapter

        fun bind(bankUi: BankItem.BankUi) {
            binding.apply {
                headerAccountTittle.text = bankUi.name
                headerAccountAmount.text = getSumBalance(bankUi.accounts)
                accountAdapter= AccountAdapter(bankUi.accounts, userClickListener)
                headerRecyclerView.adapter = accountAdapter
                itemView.setOnClickListener {
                    onItemClicked(bankUi)
                }

                if (bankUi.isExpanded!!) {
                    headerRecyclerView.visibility = View.VISIBLE
                    headerAccountImage.setImageResource(R.drawable.ic_arrow_drop_up)
                } else {
                    headerRecyclerView.visibility = View.GONE
                    headerAccountImage.setImageResource(R.drawable.ic_arrow_drop_down)
                }
            }
        }

        /**
         * Get Sum Balance
         */
        @SuppressLint("SetTextI18n")
        fun getSumBalance(accounts: List<Account>): String {
            var sum = 0.0
            accounts.forEach { account ->
                sum += account.balance
            }
            return String.format("%.2f", sum) +" \u20ac"
        }

    }

    inner class TitleViewHolder(private val binding: BankSectionTitleBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(title: BankItem.Title){
            binding.sectionAccountTitle.text = title.title
        }
    }

    override fun getItemViewType(position: Int): Int{
        return when (items?.get(position)) {
            is BankItem.BankUi -> R.layout.bank_head_item
            is BankItem.Title -> R.layout.bank_section_title
            else -> {
                throw IllegalArgumentException("Invalid getItemViewType Provided")
            }
        }
    }
}