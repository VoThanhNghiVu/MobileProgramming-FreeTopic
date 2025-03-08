package com.example.cryptotracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                        "Crypto Tracker App",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
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
                            text = { Text("Calculator") },
                            onClick = {
                                showMenu = false
                                navController.navigate("calculator")  // Dẫn đến Calculator screen
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
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(30.dp)
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
                        enabled = currentPage > 1,

                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (currentPage > 1) MaterialTheme.colorScheme.primary else Color.Gray,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ){
                       Text("Previous")
                    }

                    Text("Page $currentPage", style = MaterialTheme.typography.bodyLarge)

                    Button(
                        onClick = { viewModel.nextPage() },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,   // Màu nền của nút
                            contentColor = MaterialTheme.colorScheme.onPrimary    // Màu chữ trên nút
                        )
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
            .padding(horizontal = 50.dp, vertical = 4.dp)
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
                modifier = Modifier
                    .size(52.dp)
                    .padding(8.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(crypto.name, style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text("Price: $${crypto.current_price}", color = Color.Green)
                Text("24h change: ${crypto.price_change_percentage_24h}%", color = Color.Yellow, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}



