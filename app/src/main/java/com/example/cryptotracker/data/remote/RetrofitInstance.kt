package com.example.cryptotracker.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: CryptoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApi::class.java)
    }
}
