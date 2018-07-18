package com.teamnexters.mosaic.di

import dagger.Module

@Module(includes = [RealmModule::class])
interface DatabaseModule {
}