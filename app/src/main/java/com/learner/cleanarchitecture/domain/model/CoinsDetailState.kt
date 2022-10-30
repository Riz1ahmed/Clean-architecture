package com.learner.cleanarchitecture.domain.model

data class CoinsDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String? = null
)
