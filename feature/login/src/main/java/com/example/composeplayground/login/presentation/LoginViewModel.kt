package com.example.composeplayground.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeplayground.session.SessionUseCase
import com.example.composeplayground.session.VerifySessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionUseCase: SessionUseCase,
    private val verifySessionUseCase: VerifySessionUseCase,
) : ViewModel() {

    // Form - Section
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        _showTextFieldErrorState.value = false
    }

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password
    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        _showTextFieldErrorState.value = false
    }

    private val _showTextFieldErrorState = MutableStateFlow(false)
    val showTextFieldErrorState: StateFlow<Boolean> = _showTextFieldErrorState

    private val _keepSignedIn = MutableStateFlow(false)
    val keepSignedIn: StateFlow<Boolean> = _keepSignedIn
    fun updateKeepSignedIn(newKeepSignedIn: Boolean) {
        _keepSignedIn.value = newKeepSignedIn
    }

    // Login
    val onLoginSuccessful = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            if (verifySessionUseCase()) {
                logIn(keepSession = true)
            }
        }
    }

    fun signInClick() {
        if (password.value == "123" && email.value.isNotEmpty()) {
            logIn()
        }
    }

    private fun logIn(keepSession: Boolean = keepSignedIn.value) {
        viewModelScope.launch {
            sessionUseCase.updateIsLoggedIn(isLoggedIn = true, keepSignedIn = keepSession)
        }
        onLoginSuccessful.value = true
    }

}
