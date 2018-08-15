package com.teamnexters.mosaic.ui.result

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [ResultActivityModule.ProvideModule::class])
internal interface ResultActivityModule {
    @Module
    class ProvideModule {
        @Provides
        @ActivityScope
        fun provideResultAdapter(): ResultAdapter {
            return ResultAdapter()
        }
    }

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(ResultViewModel::class)
    fun bindResultViewModel(resultViewModel: ResultViewModel) : ViewModel
}