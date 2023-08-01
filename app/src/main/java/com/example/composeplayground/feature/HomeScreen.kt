package com.example.composeplayground.feature

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.R
import com.example.composeplayground.commons.NCard
import com.example.composeplayground.commons.NHeaderText
import com.example.composeplayground.commons.NHorizontalSpacer
import com.example.composeplayground.commons.NVerticalSpacer
import com.example.composeplayground.domain.SessionUseCase
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreen(
    sessionUseCase: SessionUseCase
) {
    val isTechnician = sessionUseCase.isTechnician.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        switchUserType(isTechnician, sessionUseCase, coroutineScope)
        if (isTechnician.value) {
            TechnicianHomeScreen()
        } else {
            EndUserHomeScreen()
        }
        NVerticalSpacer(height = 16.dp)
    }
}

@Composable
private fun switchUserType(
    isTechnician: State<Boolean>,
    sessionUseCase: SessionUseCase,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Change User",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Technician")
            Switch(
                checked = isTechnician.value,
                onCheckedChange = {
                    sessionUseCase.updateIsTechnician(coroutineScope, it)
                },
            )
        }
        NVerticalSpacer(height = 8.dp)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )
    }
}

@Composable
fun TechnicianHomeScreen() {
    Column {
        NVerticalSpacer(height = 16.dp)
        NHeaderText(
            text = "Device health",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(4) {
                    Card(
                        border = BorderStroke(width = 1.dp, color = Color.LightGray),
                        backgroundColor = Color.White,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_healthy),
                                contentDescription = ""
                            )
                            NHorizontalSpacer(width = 8.dp)
                            Text(text = "Server $it")
                        }
                    }
                }
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        NVerticalSpacer(height = 32.dp)
        NHeaderText(
            text = "Overview",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        NVerticalSpacer(height = 16.dp)
        NCard(
            icon = R.drawable.ic_organization,
            iconDescription = "",
            title = "Organizations (12)",
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        NVerticalSpacer(height = 16.dp)
        NCard(
            icon = R.drawable.ic_ticket,
            iconDescription = "",
            title = "Tickets (15)",
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun EndUserHomeScreen() {
    LazyColumn {
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
                onTicketClick = { },
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


@Composable
fun GreetingsHeader(
    modifier: Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Hello, Henri!",
            color = Color.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = "Welcome to your Dashboard",
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun TicketSection(
    ticketSectionTitle: String,
    onTicketClick: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        NCard(
            icon = R.drawable.ic_ticket,
            iconDescription = "",
            title = ticketSectionTitle,
            onClick = onTicketClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}