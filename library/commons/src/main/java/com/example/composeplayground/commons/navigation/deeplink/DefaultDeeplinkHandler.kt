package com.example.composeplayground.commons.navigation.deeplink

import android.net.Uri

class DefaultDeeplinkHandler constructor(
    private val processors: Set<@JvmSuppressWildcards IDeeplinkProcessor>
) : IDeeplinkHandler {

    override fun process(deeplink: Uri): Boolean {
        processors.forEach {
            if (it.matches(deeplink)) {
                it.execute(deeplink.toString())
                return true
            }
        }
        return false
    }

}
