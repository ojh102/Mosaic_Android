package com.teamnexters.mosaic.ui.search.keyword

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [SearchActivityModule.ProvideModule::class])
internal interface SearchActivityModule {
    @Module
    class ProvideModule {
        @Provides
        @ActivityScope
        fun provideSearchAdapter(): SearchAdapter {
            return SearchAdapter()
        }
    }

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}