package com.example.myweather.data.datastore

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.Flow

interface UserPreferenceInterface {
    suspend fun saveUnit(isChecked: MutableState<Boolean>)
    suspend fun addCity(city: String)
    val cityListFlow: Flow<List<String>>
    val userUnitFlow: Flow<Boolean>

}