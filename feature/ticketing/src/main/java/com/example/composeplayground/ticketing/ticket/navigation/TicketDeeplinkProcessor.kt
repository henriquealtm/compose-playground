package com.example.composeplayground.ticketing.ticket.navigation

import android.net.Uri
import com.example.composeplayground.commons.navigation.Navigator
import com.example.composeplayground.commons.navigation.deeplink.IDeeplinkProcessor
import com.example.composeplayground.commons.user.UserType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketDeeplinkProcessor @Inject constructor() : IDeeplinkProcessor {

    override fun matches(deeplink: Uri, userType: UserType): Boolean {
        return deeplink.toString().contains("/ticket")
                && TicketRoute.TicketList.checkPermission(userType)
    }

    override fun execute(deeplink: String, navigator: Navigator, userType: UserType) {
        navigator.navigate(TicketRoute.TicketList.getRoute(userType))
    }

}
