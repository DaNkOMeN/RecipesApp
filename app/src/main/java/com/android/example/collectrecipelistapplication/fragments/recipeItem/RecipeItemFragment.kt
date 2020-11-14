package com.android.example.collectrecipelistapplication.fragments.recipeItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.example.collectrecipelistapplication.R
import com.android.example.collectrecipelistapplication.database.RecipeDatabase
import com.android.example.collectrecipelistapplication.databinding.FragmentRecipeItemBinding


class RecipeItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecipeItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_item, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = RecipeDatabase.getInstance(application).recipeDatabaseDao()
        val factory = RecipeItemViewModelFactory(dataSource, application)

        val recipeItemViewModel1 =
            ViewModelProvider(this, factory).get(RecipeItemViewModel::class.java)

        val args =  RecipeItemFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(application, args.ingredientsList, Toast.LENGTH_LONG).show()

        binding.lifecycleOwner = this
//        binding.recipeItemViewModel = recipeItemViewModel1


        return binding.root
    }
}