package com.teamnexters.mosaic.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class MosaicSharedPreferenceManager @Inject constructor(private val sharedPreferences: SharedPreferences) {
    companion object {
        val TOKEN = "token"
        val EMAIL_ADDRESS = "emailAddress"
        val AUTH_KEY = "authKey"
        val UUID = "uuid"
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue)
    }
}