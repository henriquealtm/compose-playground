package com.example.composeplayground.commons.navigation.deeplink

import android.net.Uri
import com.example.composeplayground.commons.navigation.Navigator
import com.example.composeplayground.commons.user.UserType

class DefaultDeeplinkHandler constructor(
    private val processors: Set<@JvmSuppressWildcards IDeeplinkProcessor>,
    private val navigator: Navigator
) : IDeeplinkHandler {

    override fun process(
        deeplink: Uri,
        userType: UserType
    ): Boolean {
        processors.forEach {
            if (it.matches(deeplink, userType)) {
                it.execute(deeplink.toString(), navigator, userType)
                return true
            }
        }
        return false
    }

}
