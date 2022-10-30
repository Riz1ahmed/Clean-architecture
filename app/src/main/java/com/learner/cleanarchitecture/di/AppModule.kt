package com.learner.cleanarchitecture.di

import android.app.Application
import com.learner.cleanarchitecture.common.Const
import com.learner.cleanarchitecture.data.remote.CoinPaprikaApi
import com.learner.cleanarchitecture.data.remote.dto.coin_details.CoinDetailDto
import com.learner.cleanarchitecture.data.repo.CoinRepoImp
import com.learner.cleanarchitecture.domain.repo.CoinRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApp(@ApplicationContext app: Application): Application = app

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi =
        Retrofit
            .Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CoinPaprikaApi::class.java)


    //region Repositories
    @Provides
    @Singleton
    fun provideCoinRepo(api: CoinPaprikaApi): CoinRepo = CoinRepoImp(api)
    //endregion
}