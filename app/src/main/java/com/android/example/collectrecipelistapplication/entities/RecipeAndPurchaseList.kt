package com.android.example.collectrecipelistapplication.entities

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeAndPurchaseList(
    @Embedded
    val recipe: Recipe,

    @Relation(parentColumn = "id", entityColumn = "recipe_id")
    val unboughtPurchase : List<UnboughtPurchase> = emptyList()
)