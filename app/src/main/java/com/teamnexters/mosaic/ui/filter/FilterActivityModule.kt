package com.teamnexters.mosaic.ui.filter

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface FilterActivityModule {
    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    fun bindFilterViewModel(filterViewModel: FilterViewModel): ViewModel
}