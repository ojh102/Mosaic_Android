package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.data.remote.RemoteRepository
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindCardRepository(remoteRepository: RemoteRepository): RemoteRepositoryApi
}