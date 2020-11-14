package com.android.example.collectrecipelistapplication.fragments.recipeList

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.example.collectrecipelistapplication.entities.UnboughtPurchase
import com.android.example.collectrecipelistapplication.entities.Recipe

@BindingAdapter("recipeNameFormatted")
fun TextView.setRecipeNameFormatted(item: Recipe) {
    text = item.recipeName
}

@BindingAdapter("recipeFormatted")
fun TextView.setRecipeFormatted(item: Recipe) {
    text = item.recipe
}

@BindingAdapter("unboughtItemIdFormatted")
fun TextView.setUnboughtItemIdFormatted(item: UnboughtPurchase){
    text = item.purchaseId.toString()
}

@BindingAdapter("unboughtItemWeightFormatted")
fun TextView.setUnboughtItemWeightFormatted(item: UnboughtPurchase){
    text = item.recipeWeight
}