package com.android.example.collectrecipelistapplication.fragments.recipeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.example.collectrecipelistapplication.entities.Recipe
import com.android.example.collectrecipelistapplication.databinding.ListItemRecipeBinding

class RecipeAdapter : ListAdapter<Recipe, RecipeAdapter.ViewHolder>(
    RecipeDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : Recipe){
            binding.recipe = item
            binding.itemRecipeLayout.setOnClickListener {
                it.findNavController().navigate(RecipeListFragmentDirections.actionRecipeListFragmentToUpdateRecipeFragment(item))
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemRecipeBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class RecipeDiffCallback: DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }

}
