package com.learner.cleanarchitecture.domain.model

import com.learner.cleanarchitecture.data.remote.dto.coin_details.TeamMembers

data class CoinDetail(
    val id: String,
    val name: String,
    val desc: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMembers>
)