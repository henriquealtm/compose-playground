package com.example.composeplayground.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.composeplayground.login.presentation.LoginScreen

sealed class LoginRoute(val route: String) {
    object Graph : LoginRoute("login_graph")
    object Login : LoginRoute("login")
}

fun NavGraphBuilder.loginNavHost(
    onLoginSuccess: (destination: String?) -> Unit,
) {
    navigation(
        route = LoginRoute.Graph.route,
        startDestination = LoginRoute.Login.route,
    ) {
        composable(
            route = LoginRoute.Login.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "ninjarmm://assist/{destination}"
                },
                navDeepLink {
                    uriPattern = "https://app.ninjarmm.com/assits/{destination}"
                }
            ),
            arguments = listOf(
                navArgument(name = "destination") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val destination = backStackEntry.arguments?.getString("destination")
            // TODO clear the back stack arguments
            LoginScreen(
                onLoginSuccess = {
                    onLoginSuccess(destination)
                }
            )
        }
    }

}
