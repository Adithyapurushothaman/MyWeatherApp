package com.example.myweather.ui.theme.composable

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SwitchViewModel(private val dataStore: DataStore<Boolean>) : ViewModel() {
    private val switchStateKey = booleanPreferencesKey("switch_state")
    val switchStateFlow: Flow<Boolean> = dataStore.data
    fun onSwitchStateChanged(state: Boolean) {
        viewModelScope.launch {
            dataStore.updateData { currentBoolean ->
                !currentBoolean
            }
        }
    }
}
