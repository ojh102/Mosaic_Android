package com.teamnexters.mosaic.utils.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragment(@IdRes containerViewId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
            .add(containerViewId, fragment, tag)
            .commitNow()
}

fun AppCompatActivity.showFragment(tag: String) {
    val fragment = supportFragmentManager.findFragmentByTag(tag)

    supportFragmentManager.beginTransaction()
            .show(fragment)
            .commitNow()
}

fun AppCompatActivity.hideFragment(tag: String) {
    val fragment = supportFragmentManager.findFragmentByTag(tag)

    supportFragmentManager.beginTransaction()
            .hide(fragment)
            .commitNow()
}