package com.example.composeplayground.feature.enduser

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeplayground.R
import com.example.composeplayground.commons.GreetingsHeader
import com.example.composeplayground.commons.NCard
import com.example.composeplayground.commons.NHeaderText
import com.example.composeplayground.commons.NVerticalSpacer
import com.example.composeplayground.commons.SwitchUserTypeSection
import com.example.composeplayground.commons.TicketSection
import com.example.composeplayground.domain.SessionUseCase

@Composable
fun EndUserHomeScreen(
    onTicketCardClick: () -> Unit,
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
                icon = R.drawable.ic_support,
                iconDescription = "",
                title = "Support",
                description = "See your support options",
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}