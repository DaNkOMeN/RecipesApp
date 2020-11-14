package com.android.example.collectrecipelistapplication.fragments.recipeList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.example.collectrecipelistapplication.database.RecipeDatabase
import com.android.example.collectrecipelistapplication.entities.UnboughtPurchase
import com.android.example.collectrecipelistapplication.entities.Recipe
import com.android.example.collectrecipelistapplication.repository.PurchaseListRepository
import com.android.example.collectrecipelistapplication.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListViewModel(
    application: Application
) : AndroidViewModel(application) {


    val repository: RecipeRepository
    val purchaseRepository : PurchaseListRepository

    val allRecipes: LiveData<List<Recipe>>
    val allUnboughtPurchases : LiveData<List<UnboughtPurchase>>

   init {
       val recipeDao = RecipeDatabase.getInstance(application).recipeDatabaseDao()
       val purchaseDao = RecipeDatabase.getInstance(application).purchaseListDatabaseDao()
       repository = RecipeRepository.getObject(recipeDao)
       purchaseRepository = PurchaseListRepository.getObject(purchaseDao)

       allRecipes = repository.getRecipes()
       allUnboughtPurchases = purchaseRepository.getUnboughtPurchases()
   }

    fun insert(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(recipe)
    }

    fun update(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(recipe)
    }

    fun delete(recipe: Recipe) = viewModelScope.launch (Dispatchers.IO) {
        repository.delete(recipe)
    }

    fun deleteAll() = viewModelScope.launch (Dispatchers.IO) {
        repository.deleteAll()
    }

    fun getAllRecipesAsString(): String{
        val recipesList = mutableListOf<String>()
        allRecipes.value?.forEach {
            recipesList.add(it.recipeName)
        }

        return recipesList.toString()
    }

    fun loadAllRecipes() : String {
        val resultList = mutableListOf<String>()
        repository.loadAllRecipes().forEach {
            resultList.add(it.recipe)
        }
        return resultList.toString()
    }

    fun addRecipeToPurchaseList(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO) {
        purchaseRepository.createPurchase(recipe.id)
    }
}