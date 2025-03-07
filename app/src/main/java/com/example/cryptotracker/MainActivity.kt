package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptotracker.ui.screens.Calculator
import com.example.cryptotracker.ui.screens.CryptoScreen
import com.example.cryptotracker.ui.screens.InfoScreen
import com.example.cryptotracker.ui.theme.CryptotrackerTheme
import com.example.cryptotracker.viewmodel.CryptoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptotrackerTheme {
                val navController = rememberNavController()
                val viewModel: CryptoViewModel =
                    ViewModelProvider(this).get(CryptoViewModel::class.java)

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        CryptoScreen(navController = navController, viewModel = viewModel)
                    }
                    composable("info") {
                        InfoScreen(navController = navController)
                    }
                    composable("calculator") {
                        Calculator(navController = navController)
                    }
                }
            }
        }
    }
}