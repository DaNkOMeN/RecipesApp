package com.android.example.collectrecipelistapplication.fragments.recipeItem

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.collectrecipelistapplication.database.RecipeDatabaseDao

class RecipeItemViewModelFactory(
    private val database: RecipeDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeItemViewModel::class.java)) {
            return RecipeItemViewModel(database, application) as T
        }
        throw IllegalAccessException("Unknown ViewModelClass")
    }
}