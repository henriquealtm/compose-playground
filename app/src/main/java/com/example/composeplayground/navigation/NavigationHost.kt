package com.example.composeplayground.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.composeplayground.commons.user.UserType
import com.example.composeplayground.domain.SessionUseCase
import com.example.composeplayground.feature.enduser.EndUserHomeScreen
import com.example.composeplayground.feature.technician.TechnicianHomeScreen
import com.example.composeplayground.support.SupportScreen
import com.example.composeplayground.support.navigation.SupportRoute
import com.example.composeplayground.ticketing.ticket.navigation.TicketRoute
import com.example.composeplayground.ticketing.ticket.presentation.TicketListScreen

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    sessionUseCase: SessionUseCase,
) {
    if (sessionUseCase.isTechnician.collectAsState().value) {
        TechnicianNavigationHost(navController, sessionUseCase)
    } else {
        EndUserNavigationHost(navController, sessionUseCase)
    }
}

@Composable
fun TechnicianNavigationHost(
    navController: NavHostController,
    sessionUseCase: SessionUseCase,
) {
    NavHost(
        navController = navController,
        route = "graph",
        startDestination = "technician_home",
    ) {
        composable("technician_home") {
            TechnicianHomeScreen(
                onServerCardClick = {
                    navController.navigate("technician_server_notification_list")
                },
                onThreatCardClick = {
                    navController.navigate("technician_threat_notification_list")
                },
                onPatchCardClick = {
                    navController.navigate("technician_patch_notification_list")
                },
                onAlertCardClick = {
                    navController.navigate("technician_alert_notification_list")
                },
                onOrganizationCardClick = {
                    navController.navigate("technician_device_organization_list")
                },
                onTicketCardClick = {
                    navController.navigate(TicketRoute.TicketList.getRoute(UserType.TECHNICIAN))
                },
                sessionUseCase,
            )
        }
        composable("technician_server_notification_list") {
            Text("Server notifications")
        }
        composable("technician_threat_notification_list") {
            Text("Threat notifications")
        }
        composable("technician_patch_notification_list") {
            Text("PAAAATCH notifications")
        }
        composable("technician_alert_notification_list") {
            Text("Alert notifications")
        }
        composable("technician_device_organization_list") {
            Column {
                Text(text = "Organization 1", modifier = Modifier.padding(16.dp))
                Divider()
                Text(text = "Henri's Org", modifier = Modifier.padding(16.dp))
                Divider()
                Text(text = "Reed's Awseome Org", modifier = Modifier.padding(16.dp))
            }
        }
        composable(TicketRoute.TicketList.getRoute(UserType.TECHNICIAN)) {
            TicketListScreen()
        }
    }
}

@Composable
fun EndUserNavigationHost(
    navController: NavHostController,
    sessionUseCase: SessionUseCase,
) {
    NavHost(
        navController = navController,
        route = "graph",
        startDestination = "end_user_home",
    ) {
        composable("end_user_home") {
            EndUserHomeScreen(
                onTicketCardClick = {
                    navController.navigate(TicketRoute.TicketList.getRoute(UserType.END_USER))
                },
                onSupportCardClick = {
                    navController.navigate(SupportRoute.SupportScreen.getRoute(UserType.END_USER))
                },
                sessionUseCase,
            )
        }
        composable(TicketRoute.TicketList.getRoute(UserType.END_USER)) {
            TicketListScreen()
        }
        composable(
            SupportRoute.SupportScreen.getRoute(UserType.END_USER),
            deepLinks = SupportRoute.SupportScreen.deepLinks
        ) {
            SupportScreen()
        }
    }
}
