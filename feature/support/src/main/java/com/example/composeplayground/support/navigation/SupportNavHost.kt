package com.example.composeplayground.support.navigation

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.example.composeplayground.commons.navigation.IRoute
import com.example.composeplayground.commons.navigation.UserRoutePermission
import com.example.composeplayground.commons.navigation.checkRoutePermission
import com.example.composeplayground.commons.user.UserType

sealed class SupportRoute(
    private val path: String,
    private val permission: UserRoutePermission,
    val deepLinks: List<NavDeepLink> = listOf()
) : IRoute {

    object SupportScreen : SupportRoute(
        path = "support",
        permission = UserRoutePermission.END_USER,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "https://app.ninjarmm.com/support"
            },
            navDeepLink {
                uriPattern = "ninjarmm://assist/support"
            },
        )
    )

    override fun getRoute(userType: UserType?): String {
        return permission.getPrefix() + path
    }

    override fun checkPermission(userType: UserType): Boolean {
        return permission.checkRoutePermission(userType)
    }

}
