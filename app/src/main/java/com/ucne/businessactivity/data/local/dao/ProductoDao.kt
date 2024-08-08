package com.ucne.businessactivity.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ucne.businessactivity.data.local.entities.ProductoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Upsert
    suspend fun save(producto: ProductoEntity)

    @Delete
    suspend fun delete(producto: ProductoEntity)

    @Query(
        """
            SELECT * 
            FROM Productos
            WHERE productID = :id
            LIMIT 1
        """
    )
    suspend fun find(id: Int): ProductoEntity?

    @Query("SELECT * FROM Productos")
    fun getAll(): Flow<List<ProductoEntity>>

//    @Query(
//        """
//        SELECT 1
//        FROM Productos
//        WHERE productName=:name AND categoryID!=:servicioId
//        LIMIT 1
//        """
//    )
//    fun descripcionExiste(name: String, servicioId: Int): Flow<Boolean?>
}