package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.di.anotation.FragmentScope
import com.teamnexters.mosaic.ui.main.home.HomeFragment
import com.teamnexters.mosaic.ui.main.home.HomeFragmentModule
import com.teamnexters.mosaic.ui.main.mypage.MyPageFragment
import com.teamnexters.mosaic.ui.main.mypage.MyPageFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    @FragmentScope
    fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [MyPageFragmentModule::class])
    @FragmentScope
    fun bindMyPageFragment(): MyPageFragment
}