package com.manuelemr.galleyapp.presentation.foundation

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson

class PreferenceHandler(private val preferences: SharedPreferences) {

    var token: String?
        get() = preferences.getString("token", null)
        set(value) {
            preferences.edit {
                putString("token", value)
            }
        }
}