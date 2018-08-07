package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.data.remote.CardRepository
import com.teamnexters.mosaic.data.remote.CardRepositoryApi
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindCardRepository(cardRepository: CardRepository): CardRepositoryApi
}