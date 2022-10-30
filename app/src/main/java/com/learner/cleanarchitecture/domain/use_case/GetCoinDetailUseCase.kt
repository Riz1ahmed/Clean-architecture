package com.learner.cleanarchitecture.domain.use_case

import com.learner.cleanarchitecture.common.Resource
import com.learner.cleanarchitecture.domain.model.CoinDetail
import com.learner.cleanarchitecture.domain.repo.CoinRepo
import com.learner.cleanarchitecture.utils.toCoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repo: CoinRepo
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = repo.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coinDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected error occurred."))
        } catch (E: IOException) {
            emit(Resource.Error("Couldn't reach to server or Internet connection error."))
        }
    }
}