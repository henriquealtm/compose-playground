package com.example.composeplayground.domain

import com.example.composeplayground.commons.user.UserType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SessionUseCase {

    val isTechnician = MutableStateFlow(false)
    val userType = MutableStateFlow(UserType.END_USER)

    fun updateIsTechnician(
        scope: CoroutineScope,
        isTechnician: Boolean
    ) {
        scope.launch {
            this@SessionUseCase.isTechnician.value = isTechnician
            this@SessionUseCase.userType.value =
                if (isTechnician) UserType.TECHNICIAN else UserType.END_USER
        }
    }

}
