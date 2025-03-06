package com.example.cryptotracker.data.model

data class Crypto(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val current_price: Double,
    val price_change_percentage_24h: Double
)
