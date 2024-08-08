package com.ucne.businessactivity.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ucne.businessactivity.data.local.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Upsert
    suspend fun save(categoria: CategoryEntity)

    @Delete
    suspend fun delete(categoria: CategoryEntity)

    @Query(
        """
            SELECT * 
            FROM Categories
            WHERE categoryID = :id
            LIMIT 1
        """
    )
    suspend fun find(id: Int): CategoryEntity?

    @Query("SELECT * FROM Categories")
    fun getAll(): Flow<List<CategoryEntity>>
}