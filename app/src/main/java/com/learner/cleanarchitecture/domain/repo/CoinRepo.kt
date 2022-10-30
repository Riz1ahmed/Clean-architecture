package com.learner.cleanarchitecture.domain.repo

import com.learner.cleanarchitecture.data.remote.dto.CoinDto
import com.learner.cleanarchitecture.data.remote.dto.coin_details.CoinDetailDto

interface CoinRepo {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}