package com.example.ca_compte.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.ca_compte.databinding.BankHeadItemBinding
import com.example.ca_compte.databinding.BankSectionTitleBinding

/**
 * BankRecyclerViewHolder
 */

sealed class BankRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: BankRecyclerViewItem, position: Int) -> Unit)? = null

    /**
     *  Title ViewHolder
     */
    class TitleViewHolder(private val binding: BankSectionTitleBinding) : BankRecyclerViewHolder(binding){
        fun bind(title: BankRecyclerViewItem.Title){
            binding.sectionAccountTitle.text = title.title
        }
    }


    /**
     * Bank CA ViewHolder
     */
    class BankCAViewHolder(private val binding: BankHeadItemBinding) : BankRecyclerViewHolder(binding){
        fun bind(title: BankRecyclerViewItem.BankCA){
            binding.headerAccountTittle.text = title.name
        }
    }

}