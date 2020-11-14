package com.android.example.collectrecipelistapplication.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize


@Entity(
    tableName = "purchase_list_table",
    foreignKeys = [
        ForeignKey(entity = Recipe::class, parentColumns = ["id"], childColumns = ["recipe_id"])
    ],
    indices = [Index("recipe_id")]
)
data class UnboughtPurchase(
    @ColumnInfo (name = "recipe_id")
    val recipeId: Long,

    @ColumnInfo (name = "recipe_weight")
    val recipeWeight: String = "0",
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var purchaseId: Long = 0
}