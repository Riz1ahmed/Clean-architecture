package com.learner.cleanarchitecture.data.repo

import com.learner.cleanarchitecture.data.remote.CoinPaprikaApi
import com.learner.cleanarchitecture.data.remote.dto.CoinDto
import com.learner.cleanarchitecture.data.remote.dto.coin_details.CoinDetailDto
import com.learner.cleanarchitecture.domain.repo.CoinRepo
import javax.inject.Inject

class CoinRepoImp @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepo {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}