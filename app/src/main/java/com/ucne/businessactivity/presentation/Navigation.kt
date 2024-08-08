package com.ucne.businessactivity.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ucne.businessactivity.presentation.Screen.Home
import com.ucne.businessactivity.presentation.screen.LoginScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Home.route) {

        }
        composable(Screen.Login.route) {
            LoginScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object Login : Screen("Login")
    object Register : Screen("Register")
}