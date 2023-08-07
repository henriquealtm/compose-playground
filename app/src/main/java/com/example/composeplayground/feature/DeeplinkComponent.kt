package com.example.composeplayground.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import com.example.composeplayground.notification.Notification
import com.example.composeplayground.session.SessionUseCase
import com.example.composeplayground.ui.NHorizontalSpacer
import com.example.composeplayground.ui.NPrimaryButton
import com.example.composeplayground.ui.NVerticalSpacer
import kotlinx.coroutines.launch

const val PATH = "ninjarmm://assist/"

@Composable
fun DeeplinkComponent(
    sessionUseCase: SessionUseCase,
    onLogout: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    val uriHandler = LocalUriHandler.current
    val navigate: (handler: UriHandler, url: String) -> Unit = { handler, url ->
        handler.openUri(PATH + url)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NPrimaryButton(
                title = "Ticket",
                onClick = { navigate(uriHandler, "ticket") }
            )
            NHorizontalSpacer(width = 4.dp)
            NPrimaryButton(
                title = "Support",
                onClick = { navigate(uriHandler, "support") }
            )
            NHorizontalSpacer(width = 4.dp)
            NPrimaryButton(
                title = "Deep",
                onClick = { navigate(uriHandler, "deep") }
            )
        }
        NVerticalSpacer(height = 8.dp)
        NPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Generate notifications",
            onClick = { Notification.notify(applicationContext = context) }
        )
        NVerticalSpacer(height = 16.dp)
        NPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Logout",
            onClick = {
                coroutineScope.launch {
                    sessionUseCase.logOut()
                    onLogout()
                }
            }
        )
    }

}
