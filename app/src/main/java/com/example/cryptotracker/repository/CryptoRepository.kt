package com.example.cryptotracker.repository

import android.util.Log
import com.example.cryptotracker.data.model.Crypto
import com.example.cryptotracker.data.remote.RetrofitInstance
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.* // Dùng engine CIO
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay


class CryptoRepository {
    suspend fun getCryptoList(page: Int, perPage: Int): List<Crypto> {
        return try {
            delay(1000)
            RetrofitInstance.api.getCryptoList(perPage = perPage, page = page)
        } catch (e: Exception) {
            Log.e("CryptoRepository", "Error when get data: ${e.message}")
            emptyList()
        }
    }
}

