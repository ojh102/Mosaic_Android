package com.teamnexters.mosaic.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class MosaicSharedPreferenceManager @Inject constructor(private val sharedPreferences: SharedPreferences) {
    companion object {
        val EMAIL_ADDRESS = "emailAddress";
    }
    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue)
    }
}