package com.example.houseopscaretakers.feature_caretaker.core.presentation.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UtilsViewModel : ViewModel() {

    var isShowing = mutableStateOf<Boolean>(true)

    fun hideSplash() {

        viewModelScope.launch {
            delay(3000)
            isShowing.value = false
        }
    }
}