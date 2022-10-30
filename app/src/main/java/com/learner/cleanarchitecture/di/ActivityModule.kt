package com.learner.cleanarchitecture.di

import com.learner.cleanarchitecture.presentation.view_model.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    /*@Provides
    @ActivityScoped
    fun mainViewModelProvide(): MainViewModel {
        return MainViewModel()
    }*/
}