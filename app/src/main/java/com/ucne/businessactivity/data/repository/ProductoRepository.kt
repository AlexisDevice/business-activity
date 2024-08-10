package com.ucne.businessactivity.data.repository

import com.ucne.businessactivity.data.local.dao.ProductoDao
import com.ucne.businessactivity.data.local.entities.ProductoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductoRepository @Inject constructor(private val productoDao: ProductoDao) {
    suspend fun save(producto: ProductoEntity) = productoDao.save(producto)
    fun getProductos() : Flow<List<ProductoEntity>> = productoDao.getAll()

    suspend fun getProducto(productoID: Int) : ProductoEntity? = productoDao.find(productoID)


}