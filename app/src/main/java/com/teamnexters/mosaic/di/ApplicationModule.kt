package com.teamnexters.mosaic.di

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.teamnexters.mosaic.base.GlobalChannel
import com.teamnexters.mosaic.base.GlobalChannelApi
import com.teamnexters.mosaic.base.ViewModelFactory
import com.teamnexters.mosaic.di.qualifier.RxIOScheduler
import com.teamnexters.mosaic.di.qualifier.RxMainScheduler
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module(includes = [ApplicationModule.ProvideModule::class])
internal interface ApplicationModule {
    @Binds
    @Singleton
    fun bindContext(application: Application): Context

    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @Singleton
    fun bindGlobalChannel(globalChannel: GlobalChannel): GlobalChannelApi

    @Module
    class ProvideModule {
        @Provides
        @Singleton
        fun provideResources(context: Context): Resources {
            return context.resources
        }

        @Provides
        @Singleton
        @RxIOScheduler
        fun provideRxIOScheduler(): Scheduler {
            return Schedulers.io()
        }

        @Provides
        @Singleton
        @RxMainScheduler
        fun provideRxMainScheduler(): Scheduler {
            return AndroidSchedulers.mainThread()
        }

        @Provides
        @Singleton
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        @Provides
        @Singleton
        fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
            return sharedPreferences.edit()
        }
    }
}