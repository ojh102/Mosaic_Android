package com.teamnexters.mosaic.ui.filter

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [FilterActivityModule.ProvideModule::class])
internal interface FilterActivityModule {
    @Module
    class ProvideModule {
        @Provides
        @ActivityScope
        fun provideFilterAdapter(): FilterAdapter {
            return FilterAdapter()
        }
    }

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    fun bindFilterViewModel(filterViewModel: FilterViewModel): ViewModel
}