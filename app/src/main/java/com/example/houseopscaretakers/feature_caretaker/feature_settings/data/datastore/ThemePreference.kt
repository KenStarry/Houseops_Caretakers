package com.example.houseopscaretakers.feature_caretaker.feature_settings.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.houseopscaretakers.core.data.datastore.DatastoreConstants
import com.example.houseopscaretakers.core.data.datastore.datastore
import com.example.houseopscaretakers.feature_caretaker.feature_settings.presentation.utils.SettingsConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemePreference(
    private val context: Context
) {

    companion object {
        val THEME_KEY = stringPreferencesKey(DatastoreConstants.THEME_KEY)
    }

    val getTheme: Flow<String?> = context.datastore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: SettingsConstants.themeOptions[2].title
        }

    suspend fun setTheme(theme: String) {
        context.datastore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }
}