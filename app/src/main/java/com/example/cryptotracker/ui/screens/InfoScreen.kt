package com.example.cryptotracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Back to Crypto Tracker") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { paddingValues ->  // ✅ Lấy paddingValues từ Scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // ✅ Áp dụng padding từ Scaffold
                .padding(24.dp), // Giảm padding để tránh bị chồng chéo
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("App Info", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "This is the Info screen.\n\n" +
                        "The data source of this application is available on the website: https://www.coingecko.com\n\n" +
                        "According to the document, the CoinGecko Demo API plan has a rate limit of 30 calls/min. In fact, you can only fetch up to 5 times in 1 minute.\n\n" +
                        "When service is denied, you have to wait a few minutes before coming back.\n\n" +
                        "Developed by Vo Thanh Nghi Vu.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Blue
            )
        }
    }
}
