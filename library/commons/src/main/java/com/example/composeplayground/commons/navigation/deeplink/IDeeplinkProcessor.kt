package com.example.composeplayground.commons.navigation.deeplink

import android.net.Uri
import com.example.composeplayground.commons.navigation.Navigator
import com.example.composeplayground.commons.user.UserType

interface IDeeplinkProcessor {

    fun matches(deeplink: Uri, userType: UserType): Boolean

    fun execute(deeplink: String, navigator: Navigator, userType: UserType)

}
