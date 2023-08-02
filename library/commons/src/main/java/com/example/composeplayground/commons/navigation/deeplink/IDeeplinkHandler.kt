package com.example.composeplayground.commons.navigation.deeplink

import android.net.Uri

interface IDeeplinkHandler {

    fun process(deeplink: Uri): Boolean

}
