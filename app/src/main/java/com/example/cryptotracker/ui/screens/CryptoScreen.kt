package com.example.cryptotracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.cryptotracker.data.model.Crypto
import com.example.cryptotracker.viewmodel.CryptoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoScreen(navController: NavHostController, viewModel: CryptoViewModel) {
    val cryptoList by viewModel.cryptoList.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val currentPage by viewModel.currentPage.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Crypto Information Tracker",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                ),
                actions = {
                    IconButton(onClick = { /*showMenu = true*/ }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "More Options",
                            tint = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Info") },
                            onClick = {
                                showMenu = false
                                navController.navigate("info")  // Dẫn đến Info screen
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Settings") },
                            onClick = {
                                showMenu = false
                                navController.navigate("settings")  // Dẫn đến Settings screen
                            }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    //fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(48.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(cryptoList) { crypto ->
                        CryptoItem(crypto)
                    }
                }
            }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { viewModel.prevPage() },
                        enabled = currentPage > 1
                    ) {
                        Text("Previous")
                    }

                    Text("Page $currentPage", style = MaterialTheme.typography.bodyMedium)

                    Button(
                        onClick = { viewModel.nextPage() }
                    ) {
                        Text("Next")
                    }
                }
            }
        }
    }



@Composable
fun CryptoItem(crypto: Crypto) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF121212)),
        modifier = Modifier
            .padding(horizontal = 48.dp, vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Image(
                painter = rememberAsyncImagePainter(crypto.image),
                contentDescription = crypto.name,
                modifier = Modifier.size(50.dp)
            )
            Spacer(Modifier.width(4.dp))
            Column {
                Text(crypto.name, style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text("Price: $${crypto.current_price}", color = Color.Green)
                Text("24h change: ${crypto.price_change_percentage_24h}%", color = Color.Yellow, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}



