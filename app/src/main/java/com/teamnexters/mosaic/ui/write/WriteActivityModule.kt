package com.teamnexters.mosaic.ui.write

import android.arch.lifecycle.ViewModel
import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.di.anotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WriteActivityModule {
    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(WriteViewModel::class)
    fun bindWriteViewModel(writeViewModel: WriteViewModel): ViewModel
}