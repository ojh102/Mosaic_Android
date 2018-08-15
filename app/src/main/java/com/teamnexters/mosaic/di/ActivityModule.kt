package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.ui.main.MainActivity
import com.teamnexters.mosaic.ui.main.MainActivityModule
import com.teamnexters.mosaic.ui.search.keyword.SearchActivity
import com.teamnexters.mosaic.ui.search.keyword.SearchActivityModule
import com.teamnexters.mosaic.ui.search.result.SearchResultActivity
import com.teamnexters.mosaic.ui.search.result.SearchResultActivityModule
import com.teamnexters.mosaic.ui.splash.SplashActivity
import com.teamnexters.mosaic.ui.splash.SplashActivityModule
import com.teamnexters.mosaic.ui.write.WriteActivity
import com.teamnexters.mosaic.ui.write.WriteActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ActivityModule {
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    @ActivityScope
    fun bindSplashAcitivity(): SplashActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [WriteActivityModule::class])
    @ActivityScope
    fun bindWriteActivity(): WriteActivity

    @ContributesAndroidInjector(modules = [SearchActivityModule::class])
    @ActivityScope
    fun bindSearchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [SearchResultActivityModule::class])
    @ActivityScope
    fun bindSearchResultActivity(): SearchResultActivity
}