package com.ucne.businessactivity.presentation.screen

import kotlinx.serialization.Serializable

sealed class Screen() {
    @Serializable
    object Home : Screen()
    @Serializable
    object Login : Screen()
    @Serializable
    data class NewProducto(val productoID: Int) : Screen()
    @Serializable
    object ListaProductos : Screen()
}