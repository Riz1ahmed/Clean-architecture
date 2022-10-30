package com.learner.cleanarchitecture.utils

import com.learner.cleanarchitecture.data.remote.dto.CoinDto
import com.learner.cleanarchitecture.data.remote.dto.coin_details.CoinDetailDto
import com.learner.cleanarchitecture.domain.model.Coin
import com.learner.cleanarchitecture.domain.model.CoinDetail

fun CoinDto.toCoin() = Coin(id, isActive, name, rank, symbol)
fun CoinDetailDto.toCoinDetail() =
    CoinDetail(id, name, description, symbol, rank, is_active, tags.map { it.name }, team)