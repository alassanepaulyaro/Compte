package com.example.ca_compte.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ca_compte.R
import com.example.ca_compte.databinding.BankHeadItemBinding
import com.example.ca_compte.databinding.BankSectionTitleBinding

class BankRecyclerViewAdapter: RecyclerView.Adapter<BankRecyclerViewHolder> (){

    var items = listOf<BankRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: ((view: View, item: BankRecyclerViewItem, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankRecyclerViewHolder {
        return when(viewType){
            R.layout.bank_section_title -> BankRecyclerViewHolder.TitleViewHolder(
                BankSectionTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.bank_head_item -> BankRecyclerViewHolder.BankCAViewHolder(
                BankHeadItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BankRecyclerViewHolder, position: Int) {
        when(holder){
            is BankRecyclerViewHolder.BankCAViewHolder -> holder.bind(items[position] as BankRecyclerViewItem.BankCA)
            is BankRecyclerViewHolder.TitleViewHolder -> holder.bind(items[position] as BankRecyclerViewItem.Title)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is BankRecyclerViewItem.BankCA -> R.layout.bank_head_item
            is BankRecyclerViewItem.Title -> R.layout.bank_section_title
        }
    }
}