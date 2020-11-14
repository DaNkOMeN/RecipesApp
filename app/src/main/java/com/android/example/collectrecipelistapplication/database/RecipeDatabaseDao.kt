package com.android.example.collectrecipelistapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.example.collectrecipelistapplication.entities.Recipe

@Dao
interface  RecipeDatabaseDao {

    @Query("SELECT * FROM recipe_table ORDER BY id asc")
    fun getAllRecipe() : LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE id = :id")
    suspend fun getRecipe(id : Long) : Recipe?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("Delete from recipe_table")
    suspend fun clear()


    @Query("Select * from recipe_table")
    fun loadAllRecipes():  Array<Recipe>
}