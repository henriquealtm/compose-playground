package com.example.composeplayground.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.commons.TicketItem
import com.example.composeplayground.ui.NForwardIcon
import com.example.composeplayground.ui.NHorizontalSpacer
import com.example.composeplayground.ui.NPrimaryButton
import com.example.composeplayground.ui.NTabRow
import com.example.composeplayground.ui.NVerticalSpacer

val activeItems = listOf(
    TicketItem(
        id = 1,
        subject = "Ticket for smth",
        description = "Testing ticket",
        lastInteractionString = "What?",
        lastInteractionFormattedDate = "03/30/2023",
    ),
    TicketItem(
        id = 2,
        subject = "Ticket for smth",
        description = "Testing ticket",
        lastInteractionString = "What?",
        lastInteractionFormattedDate = "03/30/2023",
    ),
)

@Composable
fun TicketListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TicketTabs(
            activeTicketList = listOf(),
            completedTicketList = listOf(),
            allTicketList = listOf(),
            onTicketItemClick = { },
            modifier = Modifier.weight(1f)
        )
        NPrimaryButton(
            title = "Create ticket",
            onClick = { },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
    }
}

@Composable
private fun TicketTabs(
    activeTicketList: List<TicketItem>,
    completedTicketList: List<TicketItem>,
    allTicketList: List<TicketItem>,
    onTicketItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var tabIndexInViewModel by remember { mutableStateOf(0) }
    val tabs = listOf(
        "Active (3)",
        "Complete (0)",
        "All (3)",
    )

    Column(modifier = modifier.fillMaxWidth()) {
        NTabRow(
            tabIndex = tabIndexInViewModel,
            onTabIndexUpdate = { newIndex -> tabIndexInViewModel = newIndex },
            tabs = tabs,
        )
        NVerticalSpacer(height = 12.dp)
        when (tabIndexInViewModel) {
            0 -> TicketList(list = activeItems, onTicketItemClick = onTicketItemClick)
            1 -> TicketList(list = completedTicketList, onTicketItemClick = onTicketItemClick)
            2 -> TicketList(list = allTicketList, onTicketItemClick = onTicketItemClick)
        }
    }
}

@Composable
private fun TicketList(
    list: List<TicketItem>,
    onTicketItemClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(list) { item ->
            TicketListItem(
                item = item,
                onTicketItemClick = onTicketItemClick,
            )
        }
    }
}

@Composable
internal fun TicketListItem(
    item: TicketItem,
    onTicketItemClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable { onTicketItemClick(item.id) }
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(IntrinsicSize.Min)
        ) {
            NHorizontalSpacer(width = 12.dp)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "#${item.id}",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "New",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            NHorizontalSpacer(width = 16.dp)
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = item.lastInteractionString,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
                Text(
                    text = item.lastInteractionFormattedDate,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
            NHorizontalSpacer(width = 12.dp)
            NForwardIcon(color = Color.Gray)
        }
        NVerticalSpacer(height = 12.dp)
        Text(
            text = item.subject,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = item.description,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        NVerticalSpacer(height = 12.dp)
        Divider(color = Color.LightGray)
    }
}
