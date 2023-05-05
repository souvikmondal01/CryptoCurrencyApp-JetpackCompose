package com.kivous.cryptocurrencyapp_jetpackcompose.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kivous.cryptocurrencyapp_jetpackcompose.common.Constants
import com.kivous.cryptocurrencyapp_jetpackcompose.common.Resource
import com.kivous.cryptocurrencyapp_jetpackcompose.domain.use_case.get_coin.GetCoinUseCase
import com.kivous.cryptocurrencyapp_jetpackcompose.domain.use_case.get_coins.GetCoinsUseCase
import com.kivous.cryptocurrencyapp_jetpackcompose.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(
                        coin = it.data
                    )
                }

                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = it.message ?: "An unexpected error occur"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}

