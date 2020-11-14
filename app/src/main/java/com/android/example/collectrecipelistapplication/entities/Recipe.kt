package com.android.example.collectrecipelistapplication.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "recipe_table")
data class Recipe(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long = 0L,

        @ColumnInfo(name = "name")
        var recipeName: String = "Something",

        @ColumnInfo(name = "recipe")
        var recipe : String = "Something"
) : Parcelable