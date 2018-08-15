package com.teamnexters.mosaic.ui.search

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
internal interface SearchActivityModule {
    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}