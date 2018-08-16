package com.teamnexters.mosaic.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class SharedPreferenceModule {
    companion object {
        private const val TAG = "mosaic"
        private const val PREF_NAME = "$TAG.pref"
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
}