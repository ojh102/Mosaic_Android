package com.teamnexters.mosaic.di

import com.teamnexters.mosaic.data.local.MosaicRealmObservableFactory
import com.teamnexters.mosaic.data.local.RealmScheme
import com.teamnexters.mosaic.data.local.RealmTransaction
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class RealmModule {
    companion object {
        private const val REALM_NAME = "mosaic.realm"
        private const val REALM_SCHEME_VERSION = 0L
    }

    @Provides
    @Singleton
    fun provideMosaicRealmObservableFactory(): MosaicRealmObservableFactory {
        return MosaicRealmObservableFactory()
    }

    @Provides
    @Singleton
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder()
                .schemaVersion(REALM_SCHEME_VERSION)
                .name(REALM_NAME)
                .modules(RealmScheme())
//                .migration(MosaicRealmMigration())
                .deleteRealmIfMigrationNeeded()
                .compactOnLaunch()
                .build()
    }

    @Provides
    @Singleton
    fun provideRealmTransaction(realmConfiguration: RealmConfiguration): RealmTransaction {
        return RealmTransaction(realmConfiguration)
    }
}