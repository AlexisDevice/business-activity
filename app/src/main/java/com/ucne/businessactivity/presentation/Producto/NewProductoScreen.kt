package com.ucne.businessactivity.presentation.Producto

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Notification
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.ucne.businessactivity.CaptureActivityPortrait
import com.ucne.businessactivity.data.local.entities.ProductoEntity

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProductoScreen(
    viewModel: NewProductoViewModel = hiltViewModel(),
    goToHome: () -> Unit,
    productID: Int?,

    ) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.onSetProduct(productID ?: 0)
        viewModel.getProductos()
    }


    ProductRegistrationForm(uiState = uiState,
        onSaveProducto = viewModel::saveProducto,
        goToHome = goToHome,
        onProductCode = viewModel::onProductCodeChanged,
        onProductName = viewModel::onProductNameChanged,
        onProductDescription = viewModel::onProductDescriptionChanged,
        onProductPrice = viewModel::onProductPriceChanged,
        onProductStock = viewModel::onProductStockChanged
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductRegistrationForm(
    uiState: ProductoUiState,
    onSaveProducto: () -> Boolean,
    goToHome: () -> Unit,
    onProductCode: (String) -> Unit,
    onProductName: (String) -> Unit,
    onProductDescription: (String) -> Unit,
    onProductPrice: (Double) -> Unit,
    onProductStock: (Int) -> Unit,
){
    var productID by remember { mutableStateOf<Int?>(null) }
    var productCode by remember { mutableStateOf<String?>(null) }
    var productName by remember { mutableStateOf<String?>(null) }
    var productDescription by remember { mutableStateOf<String?>(null) }
    var productPrice by remember { mutableStateOf<Double?>(0.0) }
    var productStock by remember { mutableStateOf<Int?>(null) }
    var mostrarToast by remember { mutableStateOf(false) }



    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            productCode = result.contents?: "No hay resultado"
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Producto") },
                navigationIcon = {
                    IconButton(onClick = {goToHome()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            if(mostrarToast) {
                Notification("${uiState.productName} ha sido añadido correctamente")
                mostrarToast = false
            }
            Spacer(modifier = Modifier.size(100.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = uiState.productCode ?: "",
                    onValueChange = onProductCode,
                    label = { Text("Product Code") },
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        val scanOptions = ScanOptions()
                        scanOptions.setBeepEnabled(true)
                        scanOptions.setCaptureActivity(CaptureActivityPortrait::class.java)
                        scanOptions.setOrientationLocked(false)
                        scanLauncher.launch(scanOptions)
                    },
                    modifier = Modifier.size(48.dp), // Botón cuadrado de 48x48 dp
                    contentPadding = PaddingValues(5.dp), // Sin relleno
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {}
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.productName ?: "",
                onValueChange = onProductName,
                label = { Text("Product Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.productDescription ?: "",
                onValueChange = onProductDescription,
                label = { Text("Product Description") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.productPrice.toString() ?: "",
                onValueChange = {value ->
                    value.trim().toDoubleOrNull()?.let { onProductPrice(it) }
                },
                label = { Text("Product Price") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = uiState.productStock.toString() ?: "",
                onValueChange = { value ->
                    value.trim().toIntOrNull()?.let { onProductStock(it) }
                                
                                },
                label = { Text("Product Stock") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (onSaveProducto()) goToHome()
                        mostrarToast = true
                },
//                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                Text("Save Product")
            }
        }
    }


}

@Composable
private fun ProductoBody(
    uiState: ProductoUiState,
    onSaveProducto: () -> Boolean,
){ }

@Composable
fun Notification(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
}