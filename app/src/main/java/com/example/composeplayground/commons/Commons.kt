package com.example.composeplayground.commons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.R

@Composable
fun NVerticalSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}


@Composable
fun NHorizontalSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun NHeaderText(
    text: String,
    modifier: Modifier = Modifier,
){
    Text(
        text = text,
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier,
    )
}

@Composable
fun NCard(
    modifier: Modifier = Modifier,
    icon: Int,
    iconDescription: String,
    title: String,
    description: String? = null,
    onClick: () -> Unit,
) {
    Card(
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        backgroundColor = Color.White,
        modifier = modifier.clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 12.dp, bottom = 8.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(56.dp),
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    tint = Color.Black,
                    contentDescription = iconDescription
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
            ) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                )
                if (description != null) {
                    Text(
                        text = description,
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
            NForwardIcon(color = Color.Gray)
        }
    }
}

@Composable
fun NForwardIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_forward),
        contentDescription = "",
        tint = color,
        modifier = modifier
    )
}


@Composable
fun NPrimaryButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    iconDescription: String? = null,
) {
    NPrimaryButton(
        title = title,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        icon = icon,
        iconDescription = iconDescription,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White,
        )
    )
}

@Composable
private fun NPrimaryButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    iconDescription: String? = null,
    colors: ButtonColors,
    letterSpacing: TextUnit = 0.sp
) {
    Button(
        enabled = enabled,
        onClick = { onClick() },
        colors = colors,
        modifier = modifier,
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription,
            )
        }
        Text(
            text = title,
            color = colors.contentColor(enabled = enabled).value,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp),
            letterSpacing = letterSpacing
        )
    }
}

@Composable
fun NTabRow(
    tabIndex: Int, onTabIndexUpdate: (Int) -> Unit,
    tabs: List<String>,
) {
    TabRow(
        selectedTabIndex = tabIndex,
        backgroundColor = Color.White,
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        fontSize = 15.sp,
                        text = title,
                        fontWeight = FontWeight.Normal,
                        color = if (tabIndex == index) {
                            Color.Black
                        } else {
                            Color.DarkGray
                        },
                    )
                },
                selected = tabIndex == index,
                onClick = { onTabIndexUpdate(index) },
                modifier = Modifier.height(56.dp)
            )
        }
    }
}

data class TicketItem(
    val id: Int,
    val subject: String,
    val description: String,
    val lastInteractionString: String,
    val lastInteractionFormattedDate: String,
)
