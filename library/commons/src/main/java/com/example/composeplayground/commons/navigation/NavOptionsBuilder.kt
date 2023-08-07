package com.example.composeplayground.commons.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

/**
 * Pop up to top garantes that after navigating the previous route is removed from backstack
 *
 * @param navController that manages the current navigation
 */
fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}
