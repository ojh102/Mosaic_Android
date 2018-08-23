package com.teamnexters.mosaic.di

import android.app.Application
import com.teamnexters.mosaic.MosaicApplication
import com.teamnexters.mosaic.ui.common.theme.ThemeModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    FragmentModule::class,
    ServiceModule::class,
    NetworkModule::class,
    ConverterModule::class,
    RepositoryModule::class,
    DatabaseModule::class,
    ApplicationModule::class
])
interface ApplicationComponent : AndroidInjector<Application> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MosaicApplication)
}