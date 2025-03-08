package com.example.cryptotracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
                title = { Text("Info") },
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Info", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "\nThis is the Info screen.\n" +
                        "\nThe data source of this application is available on the website https://www.coingecko.com \n" +
                        "\nAccording to the document, the CoinGecko Demo API plan has a rate limit of 30 calls/min. In fact, you can only fetch up to 5 times in 1 minute.\n" +
                        "\nWhen service is denied, you have to wait a few minutes before come back.\n" +
                        "\nDeveloped by Vo Thanh Nghi Vu.",
                style = MaterialTheme.typography.bodyLarge, color = Color.Blue
            )
        }
    }
}
