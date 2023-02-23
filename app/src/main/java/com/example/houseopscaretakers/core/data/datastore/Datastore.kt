package com.example.houseopscaretakers.core.data.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.datastore by preferencesDataStore(
    name = DatastoreConstants.PREFERENCES_NAME
)