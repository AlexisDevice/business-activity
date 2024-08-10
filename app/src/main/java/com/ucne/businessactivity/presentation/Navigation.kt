package com.ucne.businessactivity.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ucne.businessactivity.presentation.Producto.NewProductoScreen
import com.ucne.businessactivity.presentation.Producto.ProductoList
import com.ucne.businessactivity.presentation.screen.HomeScreen
import com.ucne.businessactivity.presentation.screen.LoginScreen
import com.ucne.businessactivity.presentation.screen.Screen

@Composable
fun Navigation(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Login
    ) {
        composable<Screen.Home> {
            HomeScreen(
                goToNewProducto = { navHostController.navigate(Screen.NewProducto(0)) },
                goToProductoList = { navHostController.navigate(Screen.ListaProductos) }
            )
        }
        composable<Screen.ListaProductos> {
            ProductoList(
                goToHome = { navHostController.navigate(Screen.Home) }
            )
        }
        composable<Screen.NewProducto> {
            val args = it.toRoute<Screen.NewProducto>()
            NewProductoScreen(
                goToHome = { navHostController.navigate(Screen.Home) },
                productID = args.productoID
            )
        }
        composable<Screen.Login> {
            LoginScreen(
                goToHome = { navHostController.navigate(Screen.Home) }

            )
        }
    }
}
