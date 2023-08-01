package com.example.composeplayground.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private lateinit var sessionUseCase: SessionModule

@Composable
fun requireSessionModule(): SessionModule {
    if (!::sessionUseCase.isInitialized) {
        sessionUseCase =
            EntryPoints.get(
                LocalContext.current.applicationContext,
                SessionModule::class.java,
            )
    }
    return sessionUseCase
}


@Module
@InstallIn(SingletonComponent::class)
object SessionModule {

    @Provides
    @Singleton
    fun providesSessionUseCase() = SessionUseCase()

}