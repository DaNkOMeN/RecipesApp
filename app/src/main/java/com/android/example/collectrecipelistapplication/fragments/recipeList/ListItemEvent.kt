package com.android.example.collectrecipelistapplication.fragments.recipeList

sealed class ListItemEvent {
    data class OnListItemClick(val position: Int) : ListItemEvent()
    object onListItemClick : ListItemEvent()
}