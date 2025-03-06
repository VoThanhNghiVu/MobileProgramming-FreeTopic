package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptotracker.ui.screens.CryptoScreen
import com.example.cryptotracker.ui.screens.InfoScreen
import com.example.cryptotracker.ui.screens.SettingsScreen
import com.example.cryptotracker.viewmodel.CryptoViewModel

/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cryptoViewModel: CryptoViewModel = viewModel()
            CryptoScreen(cryptoViewModel)
        }
    }
}
*/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // NavController để điều hướng
            val viewModel: CryptoViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    CryptoScreen(navController = navController, viewModel = viewModel) // Trang chính
                }
                composable("info") {
                    InfoScreen(navController = navController) // Trang Info khi được click
                }
                composable("settings") {
                    SettingsScreen(navController = navController) // Trang Settings khi được click
                }
            }
        }
    }
}