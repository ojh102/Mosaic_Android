package com.teamnexters.mosaic.di

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
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
abstract class ApplicationModule {
    @Binds
    @Singleton
    abstract fun bindContext(application: Application): Context

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Module
    class ProvideModule {
        @Provides
        @Singleton
        @RxIOScheduler
        fun provideRxIOScheduler(): Scheduler {
            return Schedulers.io()
        }

        @Provides
        @Singleton
        @RxMainScheduler
        fun provideRxMainSceheduler(): Scheduler {
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