package com.teamnexters.mosaic.data.local

import com.teamnexters.mosaic.data.local.model.Keyword
import io.realm.annotations.RealmModule

@RealmModule(classes = [
    Keyword::class
])
class RealmScheme