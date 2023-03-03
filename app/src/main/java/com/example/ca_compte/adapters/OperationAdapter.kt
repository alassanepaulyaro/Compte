package com.example.ca_compte.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ca_compte.data.Operation
import com.example.ca_compte.databinding.OperationItemBinding
import java.text.SimpleDateFormat
import java.util.*

class OperationAdapter (val items: List<Operation>?) :
    RecyclerView.Adapter<OperationAdapter.OperationViewHolder>() {

    private lateinit var binding: OperationItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         binding=OperationItemBinding.inflate(inflater,parent,false)
        return OperationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        items?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount() = items?.size?: 0


    inner class OperationViewHolder(binding : OperationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item : Operation) {
            binding.apply {
                operationTitle.text = item.title
                operationAmount.text = item.amount + " \u20ac"
                operationDate.text = getDateFormat(item.date.toLong())
            }
        }
    }

    /**
     * format date
     */
    fun getDateFormat(date: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(Date(date * 1000))
    }
}