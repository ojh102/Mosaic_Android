package com.teamnexters.mosaic.ui.widget

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.teamnexters.mosaic.utils.extension.toLinearLayoutParam
import com.teamnexters.mosaic.utils.extension.toDp
import com.teamnexters.mosaic.utils.extension.toPx

/**
 * Created by daesoon.choi on 2018. 8. 22..
 */
class ImageHolderLayout : LinearLayout {

    var dividerWidth = 6

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        orientation = LinearLayout.HORIZONTAL
    }


    fun addImage(uri: Uri) {
        createImageView().also {
            addView(it)
            Glide.with(this).load(uri).into(it)
        }
    }

    private fun createImageView() = SquareImageView(context).apply {
        if (childCount > 0) {
            layoutParams.toLinearLayoutParam().apply { leftMargin = dividerWidth.toPx  }
        }
    }

    class SquareImageView(viewContext: Context?) : AppCompatImageView(viewContext) {

        init {
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT)
        }

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec)
        }
    }
}

