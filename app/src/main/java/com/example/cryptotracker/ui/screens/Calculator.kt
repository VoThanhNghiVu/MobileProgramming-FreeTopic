package com.example.cryptotracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.filled.ArrowBack
import com.example.cryptotracker.ui.theme.OuasOrange
import net.objecthunter.exp4j.ExpressionBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator(navController: NavHostController) {
    var input by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("0") }

    fun calculate() {
        if (input.text.isNotBlank()) { // Check the input text
            try {
                result = eval(input.text).toString()  // Xử lý phép toán và lấy kết quả
                input = TextFieldValue(result) // Cập nhật input để hiển thị kết quả
            } catch (e: Exception) {
                result = "Error"
            }
        } else {
            result = "Error"
        }
    }


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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = paddingValues.calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = result,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(50.dp)
            )

            // Show input value
            BasicTextField(
                value = input,
                onValueChange = { input = it },
                textStyle = MaterialTheme.typography.titleLarge,
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(50.dp)
            )

            // nút bấm cho các phép toán
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorButton("7") { input = input.copy(text = input.text + "7") }
                    CalculatorButton("8") { input = input.copy(text = input.text + "8") }
                    CalculatorButton("9") { input = input.copy(text = input.text + "9") }
                    CalculatorButton("/") { input = input.copy(text = input.text + "/") }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorButton("4") { input = input.copy(text = input.text + "4") }
                    CalculatorButton("5") { input = input.copy(text = input.text + "5") }
                    CalculatorButton("6") { input = input.copy(text = input.text + "6") }
                    CalculatorButton("*") { input = input.copy(text = input.text + "*") }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorButton("1") { input = input.copy(text = input.text + "1") }
                    CalculatorButton("2") { input = input.copy(text = input.text + "2") }
                    CalculatorButton("3") { input = input.copy(text = input.text + "3") }
                    CalculatorButton("-") { input = input.copy(text = input.text + "-") }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorButton("0") { input = input.copy(text = input.text + "0") }
                    CalculatorButton(".") { input = input.copy(text = input.text + ".") }
                    CalculatorButton("=") {
                        calculate()
                    }
                    CalculatorButton("+") { input = input.copy(text = input.text + "+") }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text("Calculator", style = MaterialTheme.typography.titleLarge)

        }
    }
}

// Calculator button
@Composable
fun CalculatorButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(80.dp),
        colors = ButtonDefaults.buttonColors(containerColor = OuasOrange)
    ) {
        Text(label, style = MaterialTheme.typography.titleLarge, color = Color.White)
    }
}

// Functions to calculate basic math operations
fun eval(expression: String): Double {
    return try {
        ExpressionBuilder(expression).build().evaluate()
    } catch (e: Exception) {
        0.0
    }
}
