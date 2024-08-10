package com.ucne.businessactivity.presentation.Producto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.businessactivity.data.local.entities.ProductoEntity
import com.ucne.businessactivity.data.repository.ProductoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewProductoViewModel @Inject constructor(
    private val repository : ProductoRepository
) : ViewModel() {
    var uiState = MutableStateFlow(ProductoUiState())
        private set

    val productID: Int = 0

    init {
        getProductos()

        viewModelScope.launch {
            val product = repository.getProducto(productID)

            product?.let {
                uiState.update {
                    it.copy(
                        productID = product.productID,
                        productCode = product.productCode,
                        productName = product.productName,
                        productDescription = product.productDescription,
                        productPrice = product.productPrice,
                    )
                }
            }
        }
    }

    fun saveProducto(): Boolean {
        viewModelScope.launch {
            repository.save(uiState.value.toEntity())
            uiState.value = ProductoUiState()
        }
        return true
    }

    fun onProductNameChanged(productName: String) {
        uiState.update {
            it.copy(productName = productName)
        }
    }

    fun onProductDescriptionChanged(productdescription: String) {
        uiState.update {
            it.copy(productDescription = productdescription)
        }
    }

    fun onProductPriceChanged(productPrice: Double) {
        uiState.update {
            it.copy(productPrice = productPrice)
        }
    }

    fun onProductStockChanged(productStock: Int) {
        uiState.update {
            it.copy(productStock = productStock)
        }
    }

    fun onProductCodeChanged(productCode: String) {
        uiState.update {
            it.copy(productCode = productCode)
        }
    }

    fun onSetProduct(productID: Int) {
        viewModelScope.launch {
            val product = repository.getProducto(productID)

            product?.let {
                uiState.update {
                    it.copy(
                        productID = product.productID,
                        productCode = product.productCode,
                        productName = product.productName,
                        productDescription = product.productDescription,
                        productPrice = product.productPrice,
                    )
                }
            }
        }
    }

    fun getProductos() {
        viewModelScope.launch {
            repository.getProductos()
                .collect { products ->
                    uiState.update {
                        it.copy(
                            productos = products
                        )
                    }
                }
        }
    }
}

data class ProductoUiState(
    val productID: Int? = null,
    var productCode: String = "",
    val productName: String = "",
    val productDescription: String = "",
    val productPrice: Double? = 0.0,
    val productStock: Int? = 0,
    val categoryID: Int? = null,

    val productos: List<ProductoEntity> = emptyList()
)

fun ProductoUiState.toEntity(): ProductoEntity {
    return ProductoEntity(
        productID = productID?:0,
        productCode = productCode,
        productName = productName,
        productDescription = productDescription,
        productPrice = productPrice?:0.0,
        productStock = productStock?:0,
        categoryID = categoryID?:0
    )
}