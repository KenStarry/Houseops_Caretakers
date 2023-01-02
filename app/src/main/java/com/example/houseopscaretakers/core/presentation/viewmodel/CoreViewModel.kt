package com.example.houseopscaretakers.core.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.use_cases.CoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val useCase: CoreUseCases
) : ViewModel() {

    private var loggedInState by mutableStateOf(false)
    private var caretaker by mutableStateOf<Caretaker?>(null)

    //  is user logged in
    fun isUserLoggedIn(): Boolean {

        viewModelScope.launch {
            loggedInState = useCase.isCaretakerLoggedIn()
        }

        return loggedInState
    }

    //  get caretaker details
    fun getCaretakerDetails(): Caretaker? {

        viewModelScope.launch {
            caretaker = useCase.getCaretaker()
        }

        return caretaker
    }
}