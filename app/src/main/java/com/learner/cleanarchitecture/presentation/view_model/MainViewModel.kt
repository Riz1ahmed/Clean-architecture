package com.learner.cleanarchitecture.presentation.view_model

import androidx.lifecycle.*
import com.learner.cleanarchitecture.common.Resource
import com.learner.cleanarchitecture.domain.model.CoinsDetailState
import com.learner.cleanarchitecture.domain.model.CoinsState
import com.learner.cleanarchitecture.domain.use_case.GetCoinDetailUseCase
import com.learner.cleanarchitecture.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var coinListUseCase: GetCoinsUseCase,
    private var coinDetailUseCase: GetCoinDetailUseCase,
    private var savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        //getCoins()
        /*savedStateHandle.get<String>(Const.COIN_ID_KEY)?.let { coinId ->
            getCoinById(coinId)
        }*/
    }

    private val _coinsState = MutableLiveData<CoinsState>()
    val coinsState: LiveData<CoinsState> = _coinsState

    fun getCoins() = viewModelScope.launch {
        coinListUseCase().collect {
            when (it) {
                is Resource.Success -> _coinsState
                    .postValue(CoinsState(coin = it.data ?: emptyList()))
                is Resource.Loading -> _coinsState
                    .postValue(CoinsState(isLoading = true))
                is Resource.Error -> _coinsState
                    .postValue(CoinsState(error = it.msg ?: "An unexpected  error code occurred."))
            }
        }
    }

    private val _coinDetailState = MutableLiveData<CoinsDetailState>()
    val coinDetailState: LiveData<CoinsDetailState> = _coinDetailState

    fun getCoinDetailById(coinId: String) = viewModelScope.launch {
        coinDetailUseCase(coinId).collect {
            when (it) {
                is Resource.Error -> _coinDetailState
                    .postValue(CoinsDetailState(error = it.msg ?: "An unexpected error occurred."))
                is Resource.Loading -> _coinDetailState
                    .postValue(CoinsDetailState(isLoading = true))
                is Resource.Success -> _coinDetailState
                    .postValue(CoinsDetailState(coinDetail = it.data))
            }
        }
    }

}