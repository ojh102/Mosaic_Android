package com.teamnexters.mosaic.ui.main.mypage

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.FragmentScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [MyPageFragmentModule.ProvideModule::class])
interface MyPageFragmentModule {
    @Module
    class ProvideModule

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(MyPageViewModel::class)
    fun bindMyPageViewModel(myPageViewModel: MyPageViewModel): ViewModel
}