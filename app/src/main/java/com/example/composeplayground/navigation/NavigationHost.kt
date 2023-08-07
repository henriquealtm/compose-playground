package com.example.composeplayground.navigation

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.composeplayground.commons.navigation.popUpToTop
import com.example.composeplayground.commons.user.UserType
import com.example.composeplayground.feature.enduser.EndUserHomeScreen
import com.example.composeplayground.feature.splash.SplashScreen
import com.example.composeplayground.feature.technician.TechnicianHomeScreen
import com.example.composeplayground.login.navigation.LoginRoute
import com.example.composeplayground.login.navigation.loginNavHost
import com.example.composeplayground.session.SessionUseCase
import com.example.composeplayground.support.SupportScreen
import com.example.composeplayground.support.navigation.SupportRoute
import com.example.composeplayground.ticketing.ticket.navigation.TicketRoute
import com.example.composeplayground.ticketing.ticket.presentation.TicketListScreen

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    deeplinkViewModel: DeeplinkViewModel,
    sessionUseCase: SessionUseCase,
) {
    val isTechnician = sessionUseCase.isTechnician.collectAsState().value
    val navigateToDeepLink: (link: String) -> Unit = {
        navController.navigate(
            NavDeepLinkRequest.Builder.fromUri(
                uri = Uri.parse("inapp://assist/$it")
            ).build()
        )
        deeplinkViewModel.clear()
    }

    NavHost(
        navController = navController,
        route = "graph",
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(onNavigateToLogin = {
                navController.navigate(LoginRoute.Graph.route) {
                    popUpToTop(navController)
                }
            })
        }
        loginNavHost(
            onLoginSuccess = { destination ->
                deeplinkViewModel.updateDestination(destination)
                val graph = if (isTechnician) "technician_graph" else "end_user_graph"
                navController.navigate(graph) {
                    popUpTo(LoginRoute.Graph.route) {
                        inclusive = true
                    }
                }
            },
        )
        if (isTechnician) {
            technicianNavigationHost(
                navController,
                sessionUseCase,
                deeplinkViewModel,
                navigateToDeepLink
            )
        } else {
            endUserNavigationHost(
                navController,
                sessionUseCase,
                deeplinkViewModel,
                navigateToDeepLink
            )
        }
    }
}

fun NavGraphBuilder.technicianNavigationHost(
    navController: NavHostController,
    sessionUseCase: SessionUseCase,
    deeplinkViewModel: DeeplinkViewModel,
    navigateToDeepLink: (link: String) -> Unit,
) {
    navigation(
        route = "technician_graph",
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
                onDeepLink = navigateToDeepLink,
                onLogout = {
                    navController.navigate("splash") {
                        popUpTo("technician_home") {
                            inclusive = true
                        }
                    }
                },
                sessionUseCase = sessionUseCase,
                deeplinkViewModel = deeplinkViewModel
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
        composable(
            route = TicketRoute.TicketList.getRoute(UserType.TECHNICIAN),
            deepLinks = listOf(navDeepLink {
                uriPattern = "inapp://assist/ticket"
            })
        ) {
            TicketListScreen()
        }
    }
}

fun NavGraphBuilder.endUserNavigationHost(
    navController: NavHostController,
    sessionUseCase: SessionUseCase,
    deeplinkViewModel: DeeplinkViewModel,
    navigateToDeepLink: (link: String) -> Unit,
) {
    navigation(
        route = "end_user_graph",
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
                onLogout = {
                    navController.navigate("splash") {
                        popUpTo("end_user_home") {
                            inclusive = true
                        }
                    }
                },
                onDeepLink = navigateToDeepLink,
                sessionUseCase = sessionUseCase,
                deeplinkViewModel = deeplinkViewModel
            )
        }
        composable(
            TicketRoute.TicketList.getRoute(UserType.END_USER),
            deepLinks = listOf(navDeepLink {
                uriPattern = "inapp://assist/ticket"
            })
        ) {
            TicketListScreen()
        }
        composable(
            SupportRoute.SupportScreen.getRoute(UserType.END_USER),
            deepLinks = SupportRoute.SupportScreen.deepLinks
        ) {
            SupportScreen(
                onDeep = {
                    navController.navigate("end_user_deep")
                }
            )
        }
        composable("end_user_deep", deepLinks = listOf(
            navDeepLink {
                uriPattern = "inapp://assist/deep"
            }
        )) {
            Column {
                Text(text = "Deep")
            }
        }
    }
}
