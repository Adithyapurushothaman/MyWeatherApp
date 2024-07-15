package com.example.myweather.data.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
object DataStoreMoule {
    private val Context.dataStore by preferencesDataStore(DataStorePreferenceStorage.PREFS_NAME)

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStorePreferenceStorage =
        DataStorePreferenceStorage(context.dataStore)
}


