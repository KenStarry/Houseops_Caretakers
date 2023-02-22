package com.example.houseopscaretakers.feature_caretaker.feature_settings.domain.repository

interface SettingsRepository {

    suspend fun logout(
        onLogout: () -> Unit
    )

    suspend fun deleteAccount()

}