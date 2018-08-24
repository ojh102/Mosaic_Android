package com.teamnexters.mosaic.utils.extension

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity

/**
 * Created by daesoon.choi on 2018. 8. 22..
 */
fun Intent.startActivity(activity: AppCompatActivity) {
    activity.startActivity(this)
}

fun Intent.startActivityForResult(activity: AppCompatActivity, resultCode: Int) {
    activity.startActivityForResult(this,resultCode)
}