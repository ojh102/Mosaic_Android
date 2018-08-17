package com.teamnexters.mosaic.ui.mypage

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface MyPageActivityModule {
    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(MyPageViewModel::class)
    fun bindMyPageViewModel(myPageViewModel: MyPageViewModel): ViewModel
}