package com.example.composeplayground.login.di

import com.example.composeplayground.session.SessionUseCase
import com.example.composeplayground.session.UserDataStore
import com.example.composeplayground.session.VerifySessionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object LoginViewModelModule {

    @ViewModelScoped
    @Provides
    fun providesVerifySessionUseCase(
        userDataStore: UserDataStore,
    ) = VerifySessionUseCase(userDataStore)

}
