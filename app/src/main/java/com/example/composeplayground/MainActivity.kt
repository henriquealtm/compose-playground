package com.example.composeplayground

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.composeplayground.navigation.DeeplinkViewModel
import com.example.composeplayground.navigation.NavigationHost
import com.example.composeplayground.notification.Notification
import com.example.composeplayground.session.SessionUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val deeplinkViewModel: DeeplinkViewModel by viewModels()

    @Inject
    lateinit var sessionUseCase: SessionUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationHost(
                sessionUseCase = sessionUseCase,
                deeplinkViewModel = deeplinkViewModel
            )
        }
        Notification.create(applicationContext)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        deeplinkViewModel.updateDestinationFromIntent(data = intent?.data?.toString())
    }

}
