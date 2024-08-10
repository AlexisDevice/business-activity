package com.ucne.businessactivity.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goToNewProducto: () -> Unit,
    goToProductoList: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú Principal") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { goToNewProducto() },
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Registrar Producto")
            }
            Button(
                onClick = { goToProductoList() },
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Consultar Inventario")
            }
            Button(
                onClick = {  },
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Registrar nuevo Usuario")
            }
        }
    }
}