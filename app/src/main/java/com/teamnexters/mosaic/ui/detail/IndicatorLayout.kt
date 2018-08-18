package com.teamnexters.mosaic.ui.detail

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class IndicatorLayout : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var spacing: Int = 0
        set(value) {
            field = value
            adjustAllSpacing()
        }

    fun addIndicator(indicator: View) {
        indicator.takeIf { it.layoutParams == null }
                ?.also { it.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT) }

        adjustLastViewSpacing(spacing)
        addView(indicator)
    }

    fun removeAllIndicator() {
        removeAllViews()
    }

    fun onSelected(pos: Int) {
        for ( i in 0 until childCount ) {
            getChildAt(i).isSelected = ( i == pos )
        }
    }


    private fun adjustAllSpacing() {
        for ( i in 0 until childCount-1 ) {
            adjustSpacing(getChildAt(i),spacing)
        }
    }

    private fun adjustSpacing(view: View, gap: Int) {

        val layoutParams = view.layoutParams

        if(layoutParams is MarginLayoutParams) {
            view.layoutParams = layoutParams.also { it.rightMargin = gap }
        }

    }

    private fun adjustLastViewSpacing(gap: Int) {
        if(childCount != 0) {
            adjustSpacing(getChildAt(childCount-1), gap)
        }
    }

}