package com.ucne.businessactivity.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Productos")
data class ProductoEntity(
    @PrimaryKey
    val productID: Int? = null,
    val productCode: String,
    val productName: String,
    val productDescription: String,
    val productPrice: Double = 0.0,
    val productStock: Int? = null,
    val categoryID: Int? = null
)
