package com.ucne.businessactivity.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.businessactivity.data.local.dao.CategoryDao
import com.ucne.businessactivity.data.local.dao.ProductoDao
import com.ucne.businessactivity.data.local.entities.CategoryEntity
import com.ucne.businessactivity.data.local.entities.ProductoEntity

@Database(
    entities = [
        ProductoEntity::class,
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BusinessDb : RoomDatabase() {
    abstract fun productodao(): ProductoDao
    abstract fun categorydao(): CategoryDao
}