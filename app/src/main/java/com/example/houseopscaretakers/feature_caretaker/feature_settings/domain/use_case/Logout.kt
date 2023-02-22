package com.example.houseopscaretakers.feature_caretaker.feature_settings.domain.use_case

import com.example.houseopscaretakers.feature_caretaker.feature_settings.domain.repository.SettingsRepository

class Logout(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(
        onLogout: () -> Unit
    ) = repository.logout(
        onLogout = onLogout
    )
}