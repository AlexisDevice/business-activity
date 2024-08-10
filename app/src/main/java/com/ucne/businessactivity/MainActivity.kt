package com.ucne.businessactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.ucne.businessactivity.data.local.database.BusinessDb
import com.ucne.businessactivity.presentation.Navigation
import com.ucne.businessactivity.ui.theme.BusinessActivityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var businessdb: BusinessDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        businessdb = databaseBuilder(this,
            BusinessDb::class.java,
            "BusinessDB").fallbackToDestructiveMigrationFrom().build()
        enableEdgeToEdge()
        setContent {
            BusinessActivityTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}