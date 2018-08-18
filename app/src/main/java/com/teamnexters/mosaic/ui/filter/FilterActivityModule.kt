package com.teamnexters.mosaic.ui.filter

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import com.teamnexters.mosaic.ui.common.theme.ThemeModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [
    FilterActivityModule.ProvideModule::class,
    ThemeModule::class
])
internal interface FilterActivityModule {
    @Module
    class ProvideModule

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    fun bindFilterViewModel(filterViewModel: FilterViewModel): ViewModel
}