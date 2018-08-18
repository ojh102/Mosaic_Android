package com.teamnexters.mosaic.ui.common.theme

import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.ui.Screen
import com.teamnexters.mosaic.ui.filter.FilterThemeAction
import com.teamnexters.mosaic.ui.write.WriteThemeAction
import dagger.Module
import dagger.Provides

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
                        else -> throw RuntimeException("이상한짓하지마!")
                    }

                    return ThemeAdapter(compatibleThemeAction)
                }
            }
        }
    }
}