package com.example.composeplayground.commons.navigation.deeplink

import android.net.Uri
import com.example.composeplayground.commons.user.UserType

interface IDeeplinkHandler {

    fun process(deeplink: Uri, userType: UserType): Boolean

}
