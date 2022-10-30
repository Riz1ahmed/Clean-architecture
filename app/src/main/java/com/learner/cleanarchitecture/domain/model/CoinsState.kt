package com.learner.cleanarchitecture.domain.model

data class CoinsState(
    val isLoading: Boolean = false,
    val coin: List<Coin> = listOf(),
    val error: String? = null
)
