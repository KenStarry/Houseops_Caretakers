package com.example.houseopscaretakers.feature_caretaker.feature_settings.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.feature_caretaker.feature_settings.domain.use_case.SettingsUseCases
import com.example.houseopscaretakers.feature_caretaker.feature_settings.presentation.utils.SettingsConstants
import com.example.houseopscaretakers.feature_caretaker.feature_settings.data.datastore.ThemePreference
import com.example.houseopscaretakers.feature_caretaker.feature_settings.domain.model.SettingsEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themePreference: ThemePreference,
    private val useCases: SettingsUseCases
) : ViewModel() {

    val themeFlow: Flow<String?> get() = themePreference.getTheme

    private val _isDropdownExpanded = mutableStateOf(false)
    val isDropdownExpanded: State<Boolean> = _isDropdownExpanded

    private val _selectedTheme = mutableStateOf(SettingsConstants.themeOptions[2].title)
    val selectedTheme: State<String> = _selectedTheme

    private val _isThemeSectionVisible = mutableStateOf(true)
    val isThemeSectionVisible: State<Boolean> = _isThemeSectionVisible

    private val _isPersonalizationSectionVisible = mutableStateOf(true)
    val isPersonalizationSectionVisible: State<Boolean> = _isPersonalizationSectionVisible

    private val _isMiscSectionVisible = mutableStateOf(true)
    val isMiscSectionVisible: State<Boolean> = _isMiscSectionVisible

    private val _isDangerSectionVisible = mutableStateOf(true)
    val isDangerSectionVisible: State<Boolean> = _isDangerSectionVisible

    //  ALERT DIALOGS
    private val _isAccentDialogVisible = mutableStateOf(false)
    val isAccentDialogVisible: State<Boolean> = _isAccentDialogVisible

    fun onEvent(event: SettingsEvents) {
        when (event) {

            is SettingsEvents.ToggleDropdownMenu -> {
                _isDropdownExpanded.value = event.isDropdownExpanded
            }

            is SettingsEvents.ToggleThemeRadioBtn -> {
                _selectedTheme.value = event.selectedTheme
            }

            is SettingsEvents.ToggleSectionVisibility -> {

                when (event.sectionTitle) {

                    SettingsConstants.settingsSections[0].sectionTitle -> {
                        _isThemeSectionVisible.value = event.isSectionVisible
                    }

                    SettingsConstants.settingsSections[1].sectionTitle -> {
                        _isPersonalizationSectionVisible.value = event.isSectionVisible
                    }

                    SettingsConstants.settingsSections[2].sectionTitle -> {
                        _isMiscSectionVisible.value = event.isSectionVisible
                    }

                    SettingsConstants.settingsSections[3].sectionTitle -> {
                        _isDangerSectionVisible.value = event.isSectionVisible
                    }
                }
            }

            is SettingsEvents.ToggleAlertDialog -> {
                when (event.alertType) {
                    SettingsConstants.ACCENT_ALERT_DIALOG -> {
                        _isAccentDialogVisible.value = event.isVisible
                    }
                }
            }

            is SettingsEvents.SetTheme -> {
                viewModelScope.launch {
                    themePreference.setTheme(event.theme)
                }
            }

            is SettingsEvents.Logout -> {
                viewModelScope.launch {
                    useCases.logout(
                        onLogout = event.onLogout
                    )
                }
            }
        }
    }
}