package com.teamnexters.mosaic.ui.splash

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [SplashActivityModule.ProvideModule::class])
interface SplashActivityModule {
    @Module
    class ProvideModule

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}