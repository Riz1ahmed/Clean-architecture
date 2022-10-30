package com.learner.cleanarchitecture.di

import androidx.lifecycle.SavedStateHandle
import com.learner.cleanarchitecture.domain.repo.CoinRepo
import com.learner.cleanarchitecture.domain.use_case.GetCoinDetailUseCase
import com.learner.cleanarchitecture.domain.use_case.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideGetCoinsUseCase(coinRepo: CoinRepo) = GetCoinsUseCase(coinRepo)

    @Provides
    fun provideGetCoinDetailUseCase(coinRepo: CoinRepo) = GetCoinDetailUseCase(coinRepo)

    //@Provides
    //@Singleton
    //fun provideSaveState() = SavedStateHandle()
}