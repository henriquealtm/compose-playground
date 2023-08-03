package com.example.composeplayground.ticketing.ticket.domain

data class TicketItem(
    val id: Int,
    val subject: String,
    val description: String,
    val lastInteractionString: String,
    val lastInteractionFormattedDate: String,
)
