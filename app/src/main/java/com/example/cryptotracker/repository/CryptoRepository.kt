package com.example.cryptotracker.repository

import android.util.Log
import com.example.cryptotracker.data.model.Crypto
import com.example.cryptotracker.data.remote.RetrofitInstance
import kotlinx.coroutines.delay

class CryptoRepository {
    suspend fun getCryptoList(page: Int, perPage: Int): List<Crypto> {
        return try {
            RetrofitInstance.api.getCryptoList(perPage = perPage, page = page)
        } catch (e: Exception) {
            Log.e("CryptoRepository", "Error when get data: ${e.message}")
            emptyList()
        }
    }
}

