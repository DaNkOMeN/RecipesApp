package com.android.example.collectrecipelistapplication.repository

import androidx.lifecycle.LiveData
import com.android.example.collectrecipelistapplication.database.RecipeDatabaseDao
import com.android.example.collectrecipelistapplication.entities.Recipe

class RecipeRepository private constructor(private val recipeDao: RecipeDatabaseDao) {

//    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipe()
   fun getRecipes() = recipeDao.getAllRecipe()

    suspend fun insert(recipe: Recipe){
        recipeDao.addRecipe(recipe)
    }

    suspend fun delete(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }

    suspend fun deleteAll(){
        recipeDao.clear()
    }

    suspend fun update(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
    }

    fun loadAllRecipes() : Array<Recipe> {
        return recipeDao.loadAllRecipes()
    }

    companion object {
        @Volatile private var instance: RecipeRepository? = null

        fun getObject(recipeDao : RecipeDatabaseDao) =
            instance?: synchronized(this) {
                instance ?: RecipeRepository(recipeDao).also { instance = it }
            }


    }

}