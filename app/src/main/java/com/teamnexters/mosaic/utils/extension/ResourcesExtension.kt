package com.teamnexters.mosaic.utils.extension

import android.content.Context

fun Context?.hasResource(resId: Int): Boolean {
    return try {
        if(this == null) {
            return false
        }
        this.resources.getResourceName(resId)

        true
    } catch (ignored: Throwable) {
        false
    }
}