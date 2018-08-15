package com.teamnexters.mosaic.ui.search.result

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [SearchResultActivityModule.ProvideModule::class])
internal interface SearchResultActivityModule {
    @Module
    class ProvideModule {
        @Provides
        @ActivityScope
        fun provideSearchResultAdapter(): SearchResultAdapter {
            return SearchResultAdapter()
        }
    }

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(SearchResultViewModel::class)
    fun bindSearchResultViewModel(searchResultViewModel: SearchResultViewModel) : ViewModel
}