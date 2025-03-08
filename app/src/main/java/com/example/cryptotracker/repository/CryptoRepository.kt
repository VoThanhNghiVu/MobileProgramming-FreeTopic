package com.example.cryptotracker.repository

import com.example.cryptotracker.data.model.Crypto
import com.example.cryptotracker.data.remote.RetrofitInstance

class CryptoRepository {
    suspend fun getCryptoList(page: Int, perPage: Int): List<Crypto>? {
        return try {
            RetrofitInstance.api.getCryptoList(perPage = perPage, page = page)
        } catch (e: retrofit2.HttpException) {
            if (e.code() == 429) {
                null
            } else {
                throw e
            }
        } catch (e: Exception) {
            throw e
        }
    }
}