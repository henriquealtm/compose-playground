package com.example.composeplayground.navigation

import com.example.composeplayground.commons.navigation.Navigator
import com.example.composeplayground.commons.navigation.deeplink.DefaultDeeplinkHandler
import com.example.composeplayground.commons.navigation.deeplink.IDeeplinkHandler
import com.example.composeplayground.commons.navigation.deeplink.IDeeplinkProcessor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun providesNavigator() = Navigator()

    @Provides
    @Singleton
    fun providesDefaultDeeplinkHandler(
        processors: Set<@JvmSuppressWildcards IDeeplinkProcessor>,
        navigator: Navigator
    ): IDeeplinkHandler = DefaultDeeplinkHandler(processors, navigator)

}
