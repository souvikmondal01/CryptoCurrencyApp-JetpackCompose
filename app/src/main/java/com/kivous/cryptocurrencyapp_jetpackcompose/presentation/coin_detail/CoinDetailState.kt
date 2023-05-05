package com.kivous.cryptocurrencyapp_jetpackcompose.presentation.coin_detail

import com.kivous.cryptocurrencyapp_jetpackcompose.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)