package com.example.cryptotracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.data.model.Crypto
import com.example.cryptotracker.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {
    private val repository = CryptoRepository()

    private val _cryptoList = MutableStateFlow<List<Crypto>>(emptyList())
    val cryptoList: StateFlow<List<Crypto>> = _cryptoList

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val perPage = 10

    init {
        fetchCryptos()
    }

    fun fetchCryptos() {
        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = null // Cleaned error before fetch

            try {
                val result = repository.getCryptoList(_currentPage.value, perPage)

                if (result != null) { // Nếu không bị lỗi 429
                    if (result.isNotEmpty()) {
                        _cryptoList.value = result
                        _errorMessage.value = null
                    } else {
                        _errorMessage.value = "No data available for page ${_currentPage.value}.\nPlease try again later."
                    }
                } else { // Error 429, result of CryptoRepository is null
                    _errorMessage.value = "You've exceeded the request limit.\nPlease try again later."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load data: ${e.message}"
            }

            _loading.value = false
        }
    }


    fun nextPage() {
        _currentPage.value += 1
        fetchCryptos()
    }

    fun prevPage() {
        if (_currentPage.value > 1) {
            _currentPage.value -= 1
            fetchCryptos()
        }
    }
}
