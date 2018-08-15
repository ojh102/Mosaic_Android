package com.teamnexters.mosaic.utils.extension

import android.content.res.Resources
import android.text.TextUtils

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val String.isEmailAddress : Boolean
    get() {
        if(TextUtils.isEmpty(this)){
            return false
        }else{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches();
        }
    }