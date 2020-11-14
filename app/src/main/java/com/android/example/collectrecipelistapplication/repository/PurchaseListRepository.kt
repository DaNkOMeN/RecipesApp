package com.android.example.collectrecipelistapplication.repository

import com.android.example.collectrecipelistapplication.database.PurchaseListDatabaseDao
import com.android.example.collectrecipelistapplication.entities.UnboughtPurchase

class PurchaseListRepository private constructor(val purchaseListDatabaseDao: PurchaseListDatabaseDao){

    suspend fun createPurchase(recipeId: Long) {
        val purchase = UnboughtPurchase(recipeId)
        purchaseListDatabaseDao.insertPurchase(purchase)
    }

    suspend fun deletePurchase(unboughtPurchase: UnboughtPurchase) = purchaseListDatabaseDao.deletePurchase(unboughtPurchase)

    fun getPurchasesRecipe() = purchaseListDatabaseDao.getRecipesPurchaseList()
    fun getUnboughtPurchases() = purchaseListDatabaseDao.getAllPurchases()

    companion object {
        private var instance: PurchaseListRepository? = null

        fun getObject(purchaseListDatabaseDao: PurchaseListDatabaseDao) =
            instance?: synchronized(this) {
                instance?: PurchaseListRepository(purchaseListDatabaseDao).also { instance = it }
            }
    }
}