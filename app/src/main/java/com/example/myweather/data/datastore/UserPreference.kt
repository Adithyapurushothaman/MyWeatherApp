package com.example.myweather.data.datastore

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreference(private val context: Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserUnit")
        val USER_UNIT_KEY = booleanPreferencesKey("user_unit")
//        val USER_CITY_KEY = stringPreferencesKey("user_city")
        val CITY_LIST_KEY = stringSetPreferencesKey("city_list")
    }

    suspend fun saveUnit(isChecked: MutableState<Boolean>) {
        context.dataStore.edit { preferences ->
            preferences[USER_UNIT_KEY] = isChecked.value
        }
    }
//    suspend fun addCityName(city: String) {
//        val cityList = cityListFlow.first().toMutableList()
//        cityList.add(city)
//        saveCityList(cityList)
//    }
//    private suspend fun saveCityList(cityList: List<String>) {
//        context.dataStore.edit { preferences ->
//            preferences[CITY_LIST_KEY] = cityList.toSet()
//        }
//    }
    suspend fun addCity(city: String) {
        context.dataStore.edit { preferences ->
            val cityList = preferences[CITY_LIST_KEY] ?: emptySet()
            val updatedCityList = cityList.toMutableSet().apply { add(city) }
            preferences[CITY_LIST_KEY] = updatedCityList
        }
    }
    suspend fun getUnitStates() : Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[USER_UNIT_KEY] ?: false
    }
    val userUnitFlow: Flow<Boolean> = context.dataStore.data.map {
        it[USER_UNIT_KEY] ?: false
    }
    val cityListFlow: Flow<List<String>> = context.dataStore.data.map { preferences ->
        (preferences[CITY_LIST_KEY] ?: emptySet()).toList()
    }
    suspend fun getUnitState() {
//        return context.dataStore.data.map { preferences ->
//            preferences[USER_UNIT_KEY] ?: true
        return context.dataStore.data.collect{
            preferences -> preferences[USER_UNIT_KEY]?: true
        }
        }




//    val userCityFlow: Flow<String> = context.dataStore.data.map { preferences ->
//        preferences[USER_CITY_KEY] ?:""
//    }

}