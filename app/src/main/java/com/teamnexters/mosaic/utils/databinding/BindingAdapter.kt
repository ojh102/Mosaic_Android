package com.teamnexters.mosaic.utils.databinding

import android.databinding.BindingAdapter
import android.support.annotation.StringRes
import android.support.annotation.StyleRes
import android.support.v4.widget.TextViewCompat
import android.support.v7.content.res.AppCompatResources
import android.widget.TextView
import com.teamnexters.mosaic.utils.extension.hasResource

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("android:text")
        fun setText(textView: TextView, @StringRes resId: Int) {
            textView.setText(resId)
        }

        @JvmStatic
        @BindingAdapter("android:textColor")
        fun setTextColor(textView: TextView, colorOrResId: Int) {
            if (textView.context.hasResource(colorOrResId)) {
                val resId = if (colorOrResId == 0) {
                    null
                } else {
                    AppCompatResources.getColorStateList(textView.context, colorOrResId)
                }

                textView.setTextColor(resId)
            } else {
                textView.setTextColor(colorOrResId)
            }
        }

        @JvmStatic
        @BindingAdapter("android:textAppearance")
        fun setTextAppearance(textView: TextView, @StyleRes resId: Int) {
            if (resId == 0) {
                return
            }

            TextViewCompat.setTextAppearance(textView, resId)
        }
    }
}