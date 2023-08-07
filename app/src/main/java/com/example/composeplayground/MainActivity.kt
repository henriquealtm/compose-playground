package com.example.composeplayground

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.domain.SessionUseCase
import com.example.composeplayground.navigation.NavigationHost
import com.example.composeplayground.notification.Notification
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sessionUseCase: SessionUseCase

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationHost(
                navController = rememberNavController().apply {
                    navController = this
                },
                sessionUseCase = sessionUseCase
            )
        }

        val deepLink = intent.getStringExtra("deepLink")

        Log.i("onCreate", intent.toString())
        Log.i("onCreate - Extras", deepLink ?: "")

        if ((deepLink ?: "").isNotEmpty()) {
            Handler().postDelayed({
//                val newIntent = Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("ninjarmm://assist/support")
//                )
                val realIntent = Intent.parseUri("ninjarmm://assist/support", Intent.FLAG_ACTIVITY_NEW_TASK).apply {
                    setAction(Intent.ACTION_VIEW)
                    setClass(this@MainActivity, MainActivity::class.java)
                }
                navController.handleDeepLink(realIntent)
            }, 5000)
        }

        Notification.create(applicationContext)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i("onNewItent", intent.toString())
        Log.i("onNewItent - Extras", intent?.getStringExtra("deepLink") ?: "")

        sessionUseCase.deepLink.value = intent?.getStringExtra("deepLink")
    }

}
