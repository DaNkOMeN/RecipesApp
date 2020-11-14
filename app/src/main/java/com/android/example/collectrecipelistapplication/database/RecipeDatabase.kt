package com.android.example.collectrecipelistapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.example.collectrecipelistapplication.entities.UnboughtPurchase
import com.android.example.collectrecipelistapplication.entities.Recipe;



@Database(entities = [Recipe::class, UnboughtPurchase::class], version = 2, exportSchema = false)
public abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDatabaseDao(): RecipeDatabaseDao
    abstract fun purchaseListDatabaseDao(): PurchaseListDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {

            return INSTANCE?: synchronized(this) {
                return INSTANCE?: buildInstance(context).also { INSTANCE = it }
            }
        }

        fun buildInstance(context: Context) : RecipeDatabase =
            Room.databaseBuilder(context, RecipeDatabase::class.java,"recipe_database" ).fallbackToDestructiveMigration().build()



    }
}
