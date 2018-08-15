package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.data.local.LocalRepository
import com.teamnexters.mosaic.data.local.LocalRepositoryApi
import com.teamnexters.mosaic.data.remote.RemoteRepository
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RepositoryModule {
    @Binds
    @Singleton
    fun bindRemoteRepository(remoteRepository: RemoteRepository): RemoteRepositoryApi

    @Binds
    @Singleton
    fun bindLocalRepository(localRepository: LocalRepository): LocalRepositoryApi
}