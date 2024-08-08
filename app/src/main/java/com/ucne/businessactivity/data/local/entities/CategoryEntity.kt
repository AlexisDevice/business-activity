package com.ucne.businessactivity.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class CategoryEntity(
    @PrimaryKey
    val categoryID: Int? = null,
    val descripcategory: String? = null
)