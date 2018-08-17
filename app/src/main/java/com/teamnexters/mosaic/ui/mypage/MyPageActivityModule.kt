package com.teamnexters.mosaic.ui.mypage

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MyPageActivityModule.ProvideModule::class])
internal interface MyPageActivityModule {
    @Module
    class ProvideModule {
        @Provides
        @ActivityScope
        fun provideMyPageAdapter(): MyPageAdapter {
            return MyPageAdapter()
        }
    }

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(MyPageViewModel::class)
    fun bindMyPageViewModel(myPageViewModel: MyPageViewModel): ViewModel
}