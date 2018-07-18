package com.teamnexters.mosaic.data.local

import io.realm.DynamicRealm
import io.realm.RealmMigration

class MosaicRealmMigration : RealmMigration {

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        val scheme = realm?.schema

        for (currentVersion in oldVersion until newVersion) {
            when (currentVersion) {
                0L -> {

                }
            }
        }
    }

}