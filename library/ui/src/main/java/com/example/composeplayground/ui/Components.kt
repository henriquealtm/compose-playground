package com.example.composeplayground.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
) {
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

@Composable
fun NTextField(
    value: String?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    placeHolderString: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessageString: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = true,
    maxLines: Int = 1,
    placeHolderTextSize: TextUnit = 14.sp
) {
    Column {
        OutlinedTextField(
            textStyle = textStyle,
            placeholder = placeHolderString?.let {
                {
                    Text(
                        text = placeHolderString,
                        color = Color.Black,
                        fontSize = placeHolderTextSize,
                        fontWeight = FontWeight.Normal,
                        textAlign = textStyle.textAlign,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    if (isError) {
                        NFieldErrorIcon()
                    }
                    if (trailingIcon != null) {
                        NHorizontalSpacer(width = 8.dp)
                        trailingIcon()
                    }
                }
            },
            visualTransformation = visualTransformation,
            value = value ?: "",
            onValueChange = onValueChange,
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            singleLine = singleLine,
            maxLines = maxLines,
        )
        if (isError && errorMessageString != null) {
            NVerticalSpacer(height = 2.dp)
            Text(
                text = errorMessageString,
                color = Color.Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Composable
fun NFieldErrorIcon() {
    Icon(
        modifier = Modifier.size(19.dp),
        painter = painterResource(id = R.drawable.ic_error),
        contentDescription = "",
        tint = Color.Red,
    )
}

@Composable
fun NCheckBox(
    text: String,
    value: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Checkbox(
            checked = value,
            onCheckedChange = { checked -> onCheckedChange(checked) },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Green,
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.Black
            ),
        )
        Text(text = text, color = Color.Black)
    }
}

@Composable
fun NLogo(
    painterLogo: Painter,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterLogo,
        contentDescription = "",
        modifier = modifier,
    )
}
