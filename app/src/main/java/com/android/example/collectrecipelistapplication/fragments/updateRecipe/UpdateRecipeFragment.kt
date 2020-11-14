package com.android.example.collectrecipelistapplication.fragments.updateRecipe

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.example.collectrecipelistapplication.R
import com.android.example.collectrecipelistapplication.databinding.FragmentUpdateRecipeBinding
import com.android.example.collectrecipelistapplication.entities.Recipe
import com.android.example.collectrecipelistapplication.fragments.recipeList.RecipeListViewModel


class UpdateRecipeFragment : Fragment() {


    private val args by navArgs<UpdateRecipeFragmentArgs>()
    private lateinit var binding :FragmentUpdateRecipeBinding
    private lateinit var viewModel: RecipeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_recipe, container, false)

        val recipe = args.currentRecipe
        binding.updateDishName.setText(recipe.recipeName)
        binding.updateDishIngridients.setText(recipe.recipe)
        viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
        binding.updateRecipeButton.setOnClickListener {
           updateRecipe()
        }

        //доабвляем меню
        setHasOptionsMenu(true)

        return binding.root
    }


    private fun updateRecipe() {
        val recipeName = binding.updateDishName.text.toString()
        val recipeIngredients = binding.updateDishIngridients.text.toString()

        if (inputCheck(recipeName, recipeIngredients)){
            val updateRecipe = Recipe(args.currentRecipe.id, recipeName, recipeIngredients)
            viewModel.update(updateRecipe)
            Toast.makeText(requireContext(), "Успешно обновлено!", Toast.LENGTH_LONG).show()
            findNavController().navigate(UpdateRecipeFragmentDirections.actionUpdateRecipeFragmentToRecipeListFragment())
        }
    }

    private fun inputCheck(dishName : String, dishRecipe : String) : Boolean {
        return !(TextUtils.isEmpty(dishName) && TextUtils.isEmpty(dishRecipe))
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete ->  deleteRecipe()
            R.id.menu_add_to_purchase_list -> addToPurchaseList()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun addToPurchaseList() {
        viewModel.addRecipeToPurchaseList(args.currentRecipe)
    }

    private fun deleteRecipe() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("ДА"){ _,_ ->
            viewModel.delete(args.currentRecipe)

            Toast.makeText(requireContext(), "Удален ${args.currentRecipe.recipeName}", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("НЕТ"){_,_ ->

        }

        builder.setTitle("Удаление ${args.currentRecipe.recipeName}")
        builder.setMessage("Вы уверены что хотите удалить ${args.currentRecipe.recipeName}?")
        builder.create().show()
    }
}

