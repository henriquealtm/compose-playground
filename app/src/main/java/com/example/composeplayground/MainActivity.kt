package com.example.composeplayground

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.commons.navigation.Navigator
import com.example.composeplayground.commons.navigation.deeplink.IDeeplinkHandler
import com.example.composeplayground.domain.SessionUseCase
import com.example.composeplayground.navigation.NavigationHost
import com.example.composeplayground.notification.Notification
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sessionUseCase: SessionUseCase

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var deeplinkHandler: IDeeplinkHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationHost(
                navController = rememberNavController().also {
                    navigator.setController(it)
                },
                sessionUseCase = sessionUseCase
            )
        }
        handleIntent(intent)
        Notification.create(applicationContext)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.data?.let {
            deeplinkHandler.process(
                deeplink = it,
                userType = sessionUseCase.userType.value
            )
        }
    }

}
