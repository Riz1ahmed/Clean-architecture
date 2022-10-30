package com.learner.cleanarchitecture.data.remote

import com.learner.cleanarchitecture.data.remote.dto.CoinDto
import com.learner.cleanarchitecture.data.remote.dto.coin_details.CoinDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}