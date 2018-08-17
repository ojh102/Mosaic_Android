package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.di.anotation.ActivityScope
import com.teamnexters.mosaic.ui.detail.DetailActivity
import com.teamnexters.mosaic.ui.detail.DetailActivityModule
import com.teamnexters.mosaic.ui.login.LoginActivity
import com.teamnexters.mosaic.ui.login.LoginActivityModule
import com.teamnexters.mosaic.ui.filter.FilterActivity
import com.teamnexters.mosaic.ui.filter.FilterActivityModule
import com.teamnexters.mosaic.ui.main.MainActivity
import com.teamnexters.mosaic.ui.main.MainActivityModule
import com.teamnexters.mosaic.ui.mypage.MyPageActivity
import com.teamnexters.mosaic.ui.mypage.MyPageActivityModule
import com.teamnexters.mosaic.ui.result.ResultActivity
import com.teamnexters.mosaic.ui.result.ResultActivityModule
import com.teamnexters.mosaic.ui.search.SearchActivity
import com.teamnexters.mosaic.ui.search.SearchActivityModule
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

    @ContributesAndroidInjector(modules = [ResultActivityModule::class])
    @ActivityScope
    fun bindResultActivity(): ResultActivity

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    @ActivityScope
    fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    @ActivityScope
    fun bindDetailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [MyPageActivityModule::class])
    @ActivityScope
    fun bindMyPageActivity(): MyPageActivity

    @ContributesAndroidInjector(modules = [FilterActivityModule::class])
    @ActivityScope
    fun bindFilterActivity(): FilterActivity
}