package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.di.anotation.FragmentScope
import com.teamnexters.mosaic.ui.main.home.HomeFragment
import com.teamnexters.mosaic.ui.main.home.HomeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    @FragmentScope
    fun bindHomeFragment(): HomeFragment
}