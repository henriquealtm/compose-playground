package com.example.composeplayground.domain

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.composeplayground.proto.StoredUser
import com.example.composeplayground.session.SessionUseCase
import com.example.composeplayground.session.UserDataStore
import com.example.composeplayground.session.proto.UserSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Singleton

private const val USER_PROTO = "user_proto"

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

    @Singleton
    @Provides
    fun provideUserProtoDataStore(
        @ApplicationContext appContext: Context
    ) = DataStoreFactory.create(
        serializer = UserSerializer,
        corruptionHandler = null,
        migrations = emptyList(),
        scope = CoroutineScope(Dispatchers.IO + Job()),
        produceFile = { appContext.dataStoreFile(USER_PROTO) }
    )

    @Singleton
    @Provides
    fun provideUserPreferences(
        dataStore: DataStore<StoredUser>
    ): UserDataStore = UserDataStore(dataStore)

    @Provides
    @Singleton
    fun providesSessionUseCase(
        userDataStore: UserDataStore
    ) = SessionUseCase(userDataStore)

}
