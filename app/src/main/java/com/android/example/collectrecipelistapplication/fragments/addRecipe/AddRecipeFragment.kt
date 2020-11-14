package com.android.example.collectrecipelistapplication.fragments.addRecipe

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.example.collectrecipelistapplication.R
import com.android.example.collectrecipelistapplication.databinding.FragmentAddRecipeBinding
import com.android.example.collectrecipelistapplication.databinding.FragmentRecipeListBinding
import com.android.example.collectrecipelistapplication.entities.Recipe
import com.android.example.collectrecipelistapplication.fragments.recipeList.RecipeListViewModel

class AddRecipeFragment : Fragment() {

    private lateinit var mAddRecipeViewModel : RecipeListViewModel
    private lateinit var binding: FragmentAddRecipeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_recipe, container, false)
        mAddRecipeViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        binding.addRecipeButton.setOnClickListener {

            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val dishName: String = binding.dishName.text.toString()
        val dishRecipe: String = binding.dishIngridients.text.toString()

        if (inputCheck(dishName, dishRecipe)) {
            val recipe = Recipe(0,dishName, dishRecipe)
            mAddRecipeViewModel.insert(recipe)
        }
    }


    private fun inputCheck(dishName : String, dishRecipe : String) : Boolean {
        return !(TextUtils.isEmpty(dishName) && TextUtils.isEmpty(dishRecipe))
    }
}