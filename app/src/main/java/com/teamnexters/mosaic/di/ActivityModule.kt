package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.anotation.ActivityScope
import com.teamnexters.mosaic.ui.splash.SplashActivity
import com.teamnexters.mosaic.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    @ActivityScope
    abstract fun bindSplashAcitivity(): SplashActivity
}