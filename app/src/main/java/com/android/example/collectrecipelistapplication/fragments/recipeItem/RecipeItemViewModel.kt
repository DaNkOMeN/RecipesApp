package com.android.example.collectrecipelistapplication.fragments.recipeItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.collectrecipelistapplication.database.RecipeDatabaseDao

class RecipeItemViewModel(
    val database: RecipeDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    var recipes = database.getAllRecipe()



    private var _ingredientList = MutableLiveData<List<String>>()
    val ingredient: LiveData<List<String>>
        get() = _ingredientList




}