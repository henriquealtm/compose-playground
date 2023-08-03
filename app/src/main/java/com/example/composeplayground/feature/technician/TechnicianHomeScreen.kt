package com.example.composeplayground.feature.technician

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeplayground.commons.SwitchUserTypeSection
import com.example.composeplayground.domain.SessionUseCase
import com.example.composeplayground.feature.DeeplinkComponent
import com.example.composeplayground.ui.NCard
import com.example.composeplayground.ui.NHeaderText
import com.example.composeplayground.ui.NHorizontalSpacer
import com.example.composeplayground.ui.NVerticalSpacer
import com.example.composeplayground.ui.uiDrawable

@Composable
fun TechnicianHomeScreen(
    onServerCardClick: () -> Unit,
    onThreatCardClick: () -> Unit,
    onPatchCardClick: () -> Unit,
    onAlertCardClick: () -> Unit,
    onOrganizationCardClick: () -> Unit,
    onTicketCardClick: () -> Unit,
    sessionUseCase: SessionUseCase,
) {
    val isTechnician = sessionUseCase.isTechnician.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        SwitchUserTypeSection(
            isTechnician = isTechnician,
            sessionUseCase = sessionUseCase,
            coroutineScope = coroutineScope
        )
        NVerticalSpacer(height = 16.dp)
        NHeaderText(
            text = "Device health",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            ServerCard(
                iconId = uiDrawable.ic_healthy,
                "Server (0)",
                modifier = Modifier.weight(1f)
            ) {
                onServerCardClick()
            }
            ServerCard(
                iconId = uiDrawable.ic_threat,
                "Threats (0)",
                modifier = Modifier.weight(1f),
            ) {
                onThreatCardClick()
            }
        }
        Row {
            ServerCard(
                iconId = uiDrawable.ic_patch,
                "Patches (12)",
                modifier = Modifier.weight(1f),
            ) {
                onPatchCardClick()
            }
            ServerCard(
                iconId = uiDrawable.ic_alert,
                "Alert (0)",
                modifier = Modifier.weight(1f),
            ) {
                onAlertCardClick()
            }
        }
        NVerticalSpacer(height = 32.dp)
        NHeaderText(
            text = "Overview",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        NVerticalSpacer(height = 16.dp)
        NCard(
            icon = uiDrawable.ic_organization,
            iconDescription = "",
            title = "Organizations (12)",
            onClick = onOrganizationCardClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        NVerticalSpacer(height = 16.dp)
        NCard(
            icon = uiDrawable.ic_ticket,
            iconDescription = "",
            title = "Tickets (15)",
            onClick = onTicketCardClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        NVerticalSpacer(height = 24.dp)
        Divider(color = Color.LightGray)
        DeeplinkComponent()
    }
}

@Composable
private fun ServerCard(
    iconId: Int,
    title: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        backgroundColor = Color.White,
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = ""
            )
            NHorizontalSpacer(width = 8.dp)
            Text(text = title)
        }
    }
}
