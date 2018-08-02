package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.ui.main.MainActivity
import com.teamnexters.mosaic.ui.main.MainActivityModule
import com.teamnexters.mosaic.ui.splash.SplashActivity
import com.teamnexters.mosaic.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    @ActivityScope
    fun bindSplashAcitivity(): SplashActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    fun bindMainActivity(): MainActivity
}