package com.example.composeplayground.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeplayground.login.R
import com.example.composeplayground.ui.NCheckBox
import com.example.composeplayground.ui.NHeaderText
import com.example.composeplayground.ui.NPrimaryButton
import com.example.composeplayground.ui.NTextField
import com.example.composeplayground.ui.NVerticalSpacer
import com.example.composeplayground.ui.uiStrings

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val keepSignedIn = viewModel.keepSignedIn.collectAsState()
    val showTextFieldErrorState = viewModel.showTextFieldErrorState.collectAsState()

    val onLoginSuccessful = viewModel.onLoginSuccessful.collectAsState(false)
    if (onLoginSuccessful.value) {
        LaunchedEffect(onLoginSuccessful.value) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .background(color = Color.White)
                .padding(32.dp)
        ) {
            NHeaderText(text = "Login", modifier = Modifier.align(Alignment.CenterHorizontally))
            NVerticalSpacer(16.dp)
            NTextField(
                placeHolderString = stringResource(uiStrings.email),
                value = email.value,
                onValueChange = viewModel::updateEmail,
                isError = showTextFieldErrorState.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            NVerticalSpacer(16.dp)
            NTextField(
                placeHolderString = stringResource(uiStrings.password),
                value = password.value,
                onValueChange = viewModel::updatePassword,
                isError = showTextFieldErrorState.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            NVerticalSpacer(16.dp)
            NPrimaryButton(
                title = stringResource(id = R.string.login_sign_in),
                onClick = viewModel::signInClick,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            )
            NCheckBox(
                text = stringResource(R.string.login_keep_me_signed_in),
                value = keepSignedIn.value,
                onCheckedChange = viewModel::updateKeepSignedIn,
                modifier = Modifier
                    .clickable { viewModel.updateKeepSignedIn(!keepSignedIn.value) }
                    .padding(horizontal = 3.dp),
            )
        }

    }

}
