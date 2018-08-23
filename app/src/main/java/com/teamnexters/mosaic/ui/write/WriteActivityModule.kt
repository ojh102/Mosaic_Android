package com.teamnexters.mosaic.ui.write

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import com.teamnexters.mosaic.ui.common.theme.ThemeModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [
    WriteActivityModule.ProvideModule::class,
    ThemeModule::class
])
internal interface WriteActivityModule {
    @Module
    class ProvideModule

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(WriteViewModel::class)
    fun bindWriteViewModel(writeViewModel: WriteViewModel): ViewModel
}