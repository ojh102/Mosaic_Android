package com.teamnexters.mosaic.utils.extension

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.showKeyboard(delay: Long = 300) {
    Handler().postDelayed({
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }, delay)
}

fun View.hideSoftKeyboard(delay: Long = 300) {
    Handler().postDelayed({
        val imm =  context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }, delay)
}
