package com.example.composeplayground.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground.domain.SessionUseCase
import com.example.composeplayground.feature.HomeScreen
import com.example.composeplayground.feature.TicketListScreen

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    sessionUseCase: SessionUseCase,
) {
    NavHost(
        navController = navController,
        route = "graph",
        startDestination = "screen_one",
    ) {
        composable("screen_one") {
            HomeScreen(sessionUseCase)
        }
        composable("screen_two") {
            TicketListScreen()
        }
    }
}