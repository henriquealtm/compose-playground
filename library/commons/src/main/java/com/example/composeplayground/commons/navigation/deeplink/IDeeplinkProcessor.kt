package com.example.composeplayground.commons.navigation.deeplink

import android.net.Uri

interface IDeeplinkProcessor {

    fun matches(deeplink: Uri): Boolean

    fun execute(deeplink: String)

}
