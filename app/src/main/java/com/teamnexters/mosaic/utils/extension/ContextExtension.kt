package com.teamnexters.mosaic.utils.extension

import android.content.Context
import android.widget.Toast

fun Context.toast(meesage: CharSequence, delay: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, meesage, delay).show()
}

fun Context.hasResource(resId: Int): Boolean {
    return try {
        this.resources.getResourceName(resId)

        true
    } catch (ignored: Throwable) {
        false
    }
}