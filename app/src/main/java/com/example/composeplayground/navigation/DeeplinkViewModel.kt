package com.example.composeplayground.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DeeplinkViewModel @Inject constructor() : ViewModel() {

    private val _destination: MutableStateFlow<String?> = MutableStateFlow(null)
    val destination: StateFlow<String?> = _destination

    fun updateDestination(deeplink: String?) {
        if (deeplink?.isEmpty() == true) return
        _destination.value = deeplink
    }

    fun updateDestinationFromIntent(data: String?) {
        data?.getDestinationFromUrl()?.let {
            _destination.value = it
        }
    }

    fun clear() {
        _destination.value = null
    }

    private fun String.getDestinationFromUrl(): String? {
        return split(
            if (contains("https")) "https://app.ninjarmm.com/assist/" else "ninjarmm://assist/"
        ).getOrNull(1)
    }

}
