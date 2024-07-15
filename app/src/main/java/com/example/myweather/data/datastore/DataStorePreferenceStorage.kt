package com.example.myweather.data.datastore

import androidx.compose.runtime.MutableState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.myweather.data.datastore.UserPreference.Companion.USER_UNIT_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreferenceStorage @Inject constructor(

    private val dataStore: DataStore<Preferences>) : UserPreferenceInterface {

    override val userUnitFlow: Flow<Boolean> = dataStore.getValue {
        it[USER_UNIT_KEY] ?: false
    }

    override val cityListFlow: Flow<List<String>> = dataStore.data.map { preferences ->
        (preferences[UserPreference.CITY_LIST_KEY] ?: emptySet()).toList()
    }


    override suspend fun addCity(city: String) {
        dataStore.edit { preferences ->
            val cityList = preferences[UserPreference.CITY_LIST_KEY] ?: emptySet()
            val updatedCityList = cityList.toMutableSet().apply { add(city) }
            preferences[UserPreference.CITY_LIST_KEY] = updatedCityList
        }
    }

    override suspend fun saveUnit(isChecked: MutableState<Boolean>) {
        dataStore.edit { preferences ->
            preferences[USER_UNIT_KEY] = isChecked.value
        }
    }
    companion object {
        const val PREFS_NAME = "my_datastore"
    }


}