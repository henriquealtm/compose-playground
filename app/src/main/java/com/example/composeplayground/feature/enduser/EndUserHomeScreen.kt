package com.example.composeplayground.feature.enduser

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeplayground.commons.SwitchUserTypeSection
import com.example.composeplayground.domain.SessionUseCase
import com.example.composeplayground.feature.DeeplinkComponent
import com.example.composeplayground.ui.GreetingsHeader
import com.example.composeplayground.ui.NCard
import com.example.composeplayground.ui.NHeaderText
import com.example.composeplayground.ui.NVerticalSpacer
import com.example.composeplayground.ui.TicketSection
import com.example.composeplayground.ui.uiDrawable

@Composable
fun EndUserHomeScreen(
    onTicketCardClick: () -> Unit,
    onSupportCardClick: () -> Unit,
    sessionUseCase: SessionUseCase,
) {
    val isTechnician = sessionUseCase.isTechnician.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        item {
            SwitchUserTypeSection(
                isTechnician = isTechnician,
                sessionUseCase = sessionUseCase,
                coroutineScope = coroutineScope
            )
        }
        item {
            NVerticalSpacer(height = 16.dp)
            GreetingsHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
        item {
            NVerticalSpacer(height = 24.dp)
            NHeaderText(
                text = "Self service",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            NVerticalSpacer(height = 16.dp)
        }
        item {
            TicketSection(
                ticketSectionTitle = "Tickets (3)",
                onTicketClick = onTicketCardClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            NVerticalSpacer(height = 16.dp)
            NCard(
                icon = uiDrawable.ic_support,
                iconDescription = "",
                title = "Support",
                description = "See your support options",
                onClick = onSupportCardClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
        item {
            NVerticalSpacer(height = 24.dp)
            Divider(color = Color.LightGray)
            DeeplinkComponent()
        }
    }
}
