package com.example.houseopscaretakers.core.presentation.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UtilsViewModel : ViewModel() {

    var isShowing = mutableStateOf<Boolean>(false)

    fun hideSplash(): Boolean {

        viewModelScope.launch {
            delay(3000)
            isShowing.value = true
        }

        return isShowing.value
    }
}