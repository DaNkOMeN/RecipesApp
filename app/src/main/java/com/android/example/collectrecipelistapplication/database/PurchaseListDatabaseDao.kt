package com.android.example.collectrecipelistapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.example.collectrecipelistapplication.entities.UnboughtPurchase
import com.android.example.collectrecipelistapplication.entities.RecipeAndPurchaseList

@Dao
interface PurchaseListDatabaseDao {

    @Query("Select * from purchase_list_table")
    fun getAllPurchases(): LiveData<List<UnboughtPurchase>>

    @Transaction
    @Query("SELECT * FROM RECIPE_TABLE WHERE id IN (SELECT DISTINCT (recipe_id) from PURCHASE_LIST_TABLE)")
    fun getRecipesPurchaseList(): LiveData<List<RecipeAndPurchaseList>>

    @Insert
    suspend fun insertPurchase(unboughtPurchase : UnboughtPurchase) : Long

    @Delete
    suspend fun deletePurchase(unboughtPurchase: UnboughtPurchase)

}