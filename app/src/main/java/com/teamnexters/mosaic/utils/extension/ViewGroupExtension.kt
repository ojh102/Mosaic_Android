package com.teamnexters.mosaic.utils.extension

import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * Created by daesoon.choi on 2018. 8. 22..
 */
fun ViewGroup.LayoutParams.toLinearLayoutParam() : LinearLayout.LayoutParams {
    return this as LinearLayout.LayoutParams
}