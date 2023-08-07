package com.example.composeplayground.session

import com.example.composeplayground.commons.user.UserType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SessionUseCase(
    private val userDataStore: UserDataStore,
) {

    val isTechnician = MutableStateFlow(false)
    val userType = MutableStateFlow(UserType.END_USER)

    val isLoggedIn = MutableStateFlow(false)

    fun updateIsTechnician(
        scope: CoroutineScope,
        isTechnician: Boolean
    ) {
        scope.launch {
            this@SessionUseCase.isTechnician.value = isTechnician
            this@SessionUseCase.userType.value =
                if (isTechnician) UserType.TECHNICIAN else UserType.END_USER

            updateIsLoggedIn(isLoggedIn = false)
            userDataStore.removeUserData()
        }
    }

    suspend fun updateIsLoggedIn(isLoggedIn: Boolean, keepSignedIn: Boolean = false) {
        this.isLoggedIn.value = isLoggedIn
        userDataStore.updateKeepSignedIn(keepSignedIn)
    }

    suspend fun logOut() {
        this.isLoggedIn.value = false
        userDataStore.updateKeepSignedIn(false)
        userDataStore.removeUserData()
    }

    suspend fun hasSignedUser() = userDataStore.keepSignedInFlow.first()

}
