package com.example.composeplayground.feature.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.composeplayground.R
import com.example.composeplayground.ui.NLogo
import kotlinx.coroutines.CoroutineScope

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    LaunchedEffect(coroutineScope) {
        onNavigateToLogin()
    }

    SplashScreen()
}

@Composable
fun SplashScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Blue,
    ) {
        NLogo(
            painterLogo = painterResource(id = R.drawable.logo),
            modifier = Modifier.scale(0.5f)
        )
    }
}
