package com.kivous.cryptocurrencyapp_jetpackcompose.domain.repository

import com.kivous.cryptocurrencyapp_jetpackcompose.data.remote.dto.CoinDetailDto
import com.kivous.cryptocurrencyapp_jetpackcompose.data.remote.dto.CoinDto
import retrofit2.http.Path

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}