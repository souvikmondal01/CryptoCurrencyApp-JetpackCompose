package com.kivous.cryptocurrencyapp_jetpackcompose.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kivous.cryptocurrencyapp_jetpackcompose.common.Resource
import com.kivous.cryptocurrencyapp_jetpackcompose.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = CoinListState(
                        coins = it.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = it.message ?: "An unexpected error occur"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

}