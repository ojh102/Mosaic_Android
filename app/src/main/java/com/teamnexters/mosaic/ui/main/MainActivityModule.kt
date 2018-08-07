package com.teamnexters.mosaic.ui.main

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MainActivityModule.ProvideModule::class])
interface MainActivityModule {
    @Module
    class ProvideModule {
        @Provides
        @ActivityScope
        fun provideCardAdapter(): CardAdapter {
            return CardAdapter()
        }
    }

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}