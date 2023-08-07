package com.example.composeplayground.ticketing.ticket.navigation

import com.example.composeplayground.commons.navigation.IRoute
import com.example.composeplayground.commons.navigation.UserRoutePermission
import com.example.composeplayground.commons.navigation.checkRoutePermission
import com.example.composeplayground.commons.user.UserType

sealed class TicketRoute(
    private val path: String,
    private val permission: UserRoutePermission
) : IRoute {

    object TicketList : TicketRoute("ticket_list", UserRoutePermission.ALL)

    override fun getRoute(userType: UserType?): String {
        // permission.checkRoutePermission(userType)  ->  TODO maybe throw an exception?
        return permission.getPrefix() + path
    }

    override fun checkPermission(userType: UserType): Boolean {
        return permission.checkRoutePermission(userType)
    }

}
