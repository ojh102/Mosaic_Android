package com.teamnexters.mosaic.ui.common.theme

import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.ui.Screen
import com.teamnexters.mosaic.ui.filter.FilterThemeAction
import com.teamnexters.mosaic.ui.write.WriteThemeAction
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ThemeModule.ProvideModule::class])
internal interface ThemeModule {
    @Module
    class ProvideModule {
        @Provides
        @ActivityScope
        fun provideThemeAdapterFactory(): ThemeAdapter.Factory {
            return object: ThemeAdapter.Factory {
                override fun newInstance(screen: Screen): ThemeAdapter {
                    val compatibleThemeAction = when(screen) {
                        Screen.Filter -> FilterThemeAction()
                        Screen.Write -> WriteThemeAction()
                        else -> throw UnDefinedThemeValueException()
                    }

                    return ThemeAdapter(compatibleThemeAction)
                }
            }
        }

        @Provides
        @Named("filter")
        fun provideFilterThemeAdapter(factory: ThemeAdapter.Factory) : ThemeAdapter {
            return factory.newInstance(Screen.Filter)
        }

        @Provides
        @Named("write")
        fun provideWriteThemeAdapter(factory: ThemeAdapter.Factory) : ThemeAdapter {
            return factory.newInstance(Screen.Write)
        }
    }
}