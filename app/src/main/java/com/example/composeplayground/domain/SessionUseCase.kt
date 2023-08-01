package com.example.composeplayground.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SessionUseCase {

    val isTechnician = MutableStateFlow(false)

    fun updateIsTechnician(
        scope: CoroutineScope,
        isTechnician: Boolean
    ) {
        scope.launch {
            this@SessionUseCase.isTechnician.value = isTechnician
        }
    }

}