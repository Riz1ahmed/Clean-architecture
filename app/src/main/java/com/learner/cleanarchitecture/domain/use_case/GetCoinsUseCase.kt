package com.learner.cleanarchitecture.domain.use_case

import com.learner.cleanarchitecture.common.Resource
import com.learner.cleanarchitecture.data.repo.CoinRepoImp
import com.learner.cleanarchitecture.domain.model.Coin
import com.learner.cleanarchitecture.domain.repo.CoinRepo
import com.learner.cleanarchitecture.utils.toCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repo: CoinRepo
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repo.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected error occurred."))
        } catch (E: IOException) {
            emit(Resource.Error("Couldn't reach to server or Internet connection error."))
        }
    }
}