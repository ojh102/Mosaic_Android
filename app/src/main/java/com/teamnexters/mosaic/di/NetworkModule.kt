package com.teamnexters.mosaic.di

import dagger.Module

@Module(includes = [
    HttpModule::class,
    RetrofitModule::class
])
class NetworkModule {

}