package com.example.cryptotracker.data.remote

import com.example.cryptotracker.data.model.Crypto
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {
    @GET("coins/markets")
    suspend fun getCryptoList(
        @Query("vs_currency") currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sparkline") sparkline: Boolean = false
    ): List<Crypto>
}
