package com.example.composeplayground.support.navigation

import android.net.Uri
import com.example.composeplayground.commons.navigation.Navigator
import com.example.composeplayground.commons.navigation.deeplink.IDeeplinkProcessor
import com.example.composeplayground.commons.user.UserType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupportDeeplinkProcessor @Inject constructor() : IDeeplinkProcessor {

    override fun matches(deeplink: Uri, userType: UserType): Boolean {
        return deeplink.toString().contains("/support")
                && SupportRoute.SupportScreen.checkPermission(userType)
    }

    override fun execute(deeplink: String, navigator: Navigator, userType: UserType) {
        // TODO enable again
        // navigator.navigate(SupportRoute.SupportScreen.getRoute(userType))
    }

}
