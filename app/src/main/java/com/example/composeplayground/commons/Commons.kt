package com.example.composeplayground.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.domain.SessionUseCase
import com.example.composeplayground.ui.NVerticalSpacer
import kotlinx.coroutines.CoroutineScope

@Composable
fun SwitchUserTypeSection(
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

data class TicketItem(
    val id: Int,
    val subject: String,
    val description: String,
    val lastInteractionString: String,
    val lastInteractionFormattedDate: String,
)
