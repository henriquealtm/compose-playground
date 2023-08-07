package com.example.composeplayground.session

import androidx.datastore.core.DataStore
import com.example.composeplayground.proto.StoredUser
import kotlinx.coroutines.flow.map

/**
 * User data store handles the user info saved in the application cache
 *
 */
class UserDataStore(
    private val dataStore: DataStore<StoredUser>,
) {

    val keepSignedInFlow = dataStore.data.map { user -> user.keepSignedIn }

    /**
     * Update keep signed in
     *
     * @param keepSignedIn
     */
    suspend fun updateKeepSignedIn(keepSignedIn: Boolean) {
        dataStore.updateData {
            it.toBuilder().setKeepSignedIn(keepSignedIn).build()
        }
    }

    val sessionKeyFlow = dataStore.data.map { user -> user.sessionKey }

    /**
     * Update session key
     *
     * @param appSession
     */
    suspend fun updateSessionKey(appSession: String) {
        dataStore.updateData {
            it.toBuilder().setSessionKey(appSession).build()
        }
    }

    val userIdFlow = dataStore.data.map { user -> user.userId }

    /**
     * Update user id
     *
     * @param userId
     */
    suspend fun updateUserId(userId: Int) {
        dataStore.updateData {
            it.toBuilder().setUserId(userId).build()
        }
    }

    val userUidFlow = dataStore.data.map { user -> user.userUid }

    /**
     * Update user uid
     *
     * @param userUid
     */
    suspend fun updateUserUid(userUid: String) {
        dataStore.updateData {
            it.toBuilder().setUserUid(userUid).build()
        }
    }

    /**
     * Remove user data from cache
     *
     */
    suspend fun removeUserData() {
        dataStore.updateData {
            it.toBuilder()
                .clearKeepSignedIn()
                .clearSessionKey()
                .clearUserId()
                .clearUserUid()
                .build()
        }
    }

}