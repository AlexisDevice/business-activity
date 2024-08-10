package com.ucne.businessactivity.presentation.Producto

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ucne.businessactivity.data.local.entities.ProductoEntity

@Composable
fun ProductoList(
    viewModel: NewProductoViewModel = hiltViewModel(),
    goToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductoListBody(
        productos = uiState.productos,
        uistate = uiState,
        goToHome = goToHome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoListBody(
    productos: List<ProductoEntity>,
    uistate: ProductoUiState,
    goToHome: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = "Lista de Productos")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {goToHome()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Id",
                    modifier = Modifier.weight(0.20f)
                )

                Text(
                    text = "Nombre",
                    modifier = Modifier.weight(0.40f)
                )

                Text(
                    text = "Descripcion",
                    modifier = Modifier.weight(0.70f)
                )

                Text(
                    text = "Precio",
                    modifier = Modifier.weight(0.40f)
                )

                Text(
                    text = "Stock",
                    modifier = Modifier.weight(0.40f)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(productos) { item ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                        .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            text = item.productID.toString(),
                            modifier = Modifier.weight(0.20f)
                        )
                        Text(
                            text = item.productName,
                            modifier = Modifier.weight(0.40f)
                        )
                        Text(
                            text = item.productDescription,
                            modifier = Modifier.weight(0.70f)
                        )
                        Text(
                            text = item.productPrice.toString(),
                            modifier = Modifier.weight(0.70f)
                        )
                        Text(
                            text = item.productStock.toString(),
                            modifier = Modifier.weight(0.40f)
                        )
                    }
                }
            }
        }
    }
}