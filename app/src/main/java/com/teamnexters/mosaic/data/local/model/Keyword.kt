package com.teamnexters.mosaic.data.local.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


internal open class Keyword : RealmObject() {
    @PrimaryKey
    var keyword: String = ""

    var createdAt: Long = 0L
}