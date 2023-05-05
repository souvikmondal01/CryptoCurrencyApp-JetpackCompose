package com.kivous.cryptocurrencyapp_jetpackcompose.data.repository

import com.kivous.cryptocurrencyapp_jetpackcompose.data.remote.CoinPaprikaApi
import com.kivous.cryptocurrencyapp_jetpackcompose.data.remote.dto.CoinDetailDto
import com.kivous.cryptocurrencyapp_jetpackcompose.data.remote.dto.CoinDto
import com.kivous.cryptocurrencyapp_jetpackcompose.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImp @Inject constructor(
    private val api:CoinPaprikaApi
) :CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
       return api.getCoinById(coinId)
    }
}