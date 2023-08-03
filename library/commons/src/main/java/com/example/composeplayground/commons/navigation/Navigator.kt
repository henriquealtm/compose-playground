package com.example.composeplayground.commons.navigation

import androidx.navigation.NavHostController

class Navigator {

    private var navController: NavHostController? = null

    fun setController(controller: NavHostController) {
        navController = controller
    }

    fun navigate(route: String) {
        navController?.navigate(route)
    }

}
