package com.android.example.collectrecipelistapplication.fragments.recipeList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.example.collectrecipelistapplication.R
import com.android.example.collectrecipelistapplication.databinding.FragmentRecipeListBinding


class RecipeListFragment : Fragment() {

    private lateinit var mRecipeListViewModel: RecipeListViewModel
    private lateinit var allRecipeAsString : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecipeListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list, container, false)


        val adapter = RecipeAdapter()
        val recipesList = binding.recipesList
        recipesList.adapter = adapter


        mRecipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
        mRecipeListViewModel.allRecipes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.addRecipe.setOnClickListener {
            findNavController().navigate(R.id.action_recipeListFragment_to_addRecipeFragment)
        }

        binding.toNextFragment.setOnClickListener {
            findNavController().navigate(R.id.action_recipeListFragment_to_unboughtPurchaseFragment)
        }

        binding.deleteAllRecipes.setOnClickListener {
            mRecipeListViewModel.deleteAll()
        }
        allRecipeAsString = mRecipeListViewModel.getAllRecipesAsString()
        Toast.makeText(requireContext(),allRecipeAsString, Toast.LENGTH_LONG).show()
        return binding.root
    }


}