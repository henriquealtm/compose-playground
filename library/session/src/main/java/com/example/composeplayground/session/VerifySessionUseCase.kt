package com.example.composeplayground.session

import kotlinx.coroutines.flow.first

class VerifySessionUseCase(
    private val userDataStore: UserDataStore
) {

    suspend operator fun invoke(): Boolean {
        return userDataStore.keepSignedInFlow.first()
    }

}
