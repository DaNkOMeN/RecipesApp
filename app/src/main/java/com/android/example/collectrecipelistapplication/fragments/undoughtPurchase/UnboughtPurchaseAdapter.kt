package com.android.example.collectrecipelistapplication.fragments.undoughtPurchase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.collectrecipelistapplication.databinding.ListItemUnboughtPurchasesBinding
import com.android.example.collectrecipelistapplication.entities.UnboughtPurchase

class UnboughtPurchaseAdapter : ListAdapter<UnboughtPurchase, UnboughtPurchaseAdapter.ViewHolder>(
    UnboughtPurchaseDiffCallback()
){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolder {
       return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemUnboughtPurchasesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(unbought: UnboughtPurchase){
            binding.unboughtPurchase = unbought
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemUnboughtPurchasesBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}


class UnboughtPurchaseDiffCallback: DiffUtil.ItemCallback<UnboughtPurchase>() {
    override fun areItemsTheSame(
        oldItem: UnboughtPurchase,
        newItem: UnboughtPurchase
    ): Boolean {
        return oldItem.purchaseId == newItem.purchaseId
    }

    override fun areContentsTheSame(
        oldItem: UnboughtPurchase,
        newItem: UnboughtPurchase
    ): Boolean {
        return oldItem == newItem
    }
}

