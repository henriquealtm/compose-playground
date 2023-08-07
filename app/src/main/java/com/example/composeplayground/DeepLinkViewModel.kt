package com.example.composeplayground

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DeepLinkViewModel: ViewModel() {

    val deepLink = MutableStateFlow<String?>(null)

}