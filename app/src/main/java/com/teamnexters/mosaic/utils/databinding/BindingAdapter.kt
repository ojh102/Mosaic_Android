package com.teamnexters.mosaic.utils.databinding

import android.databinding.BindingAdapter
import android.graphics.drawable.ColorDrawable
import android.support.annotation.StringRes
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.content.res.AppCompatResources
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.teamnexters.mosaic.utils.extension.hasResource
import io.reactivex.functions.Action
import jp.wasabeef.glide.transformations.BlurTransformation

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
        @BindingAdapter("android:background")
        fun setBackground(view :View, colorOrResId: Int) {
            if (view.context.hasResource(colorOrResId)) {
                ViewCompat.setBackground(view, ContextCompat.getDrawable(view.context, colorOrResId))
            } else {
                ViewCompat.setBackground(view, ColorDrawable(colorOrResId))
            }
        }

        @JvmStatic
        @BindingAdapter("srcCompat")
        fun setSrcCompat(imageView: ImageView, colorOrResId: Int) {
            val drawable = if (imageView.context.hasResource(colorOrResId)) {
                ContextCompat.getDrawable(imageView.context, colorOrResId)
            } else {
                ColorDrawable(colorOrResId)
            }

            imageView.setImageDrawable(drawable)
        }

        @JvmStatic
        @BindingAdapter("android:textAppearance")
        fun setTextAppearance(textView: TextView, @StyleRes resId: Int) {
            if (resId == 0) {
                return
            }

            TextViewCompat.setTextAppearance(textView, resId)
        }

        @JvmStatic
        @BindingAdapter(value = [
            "srcCompat", "useBlur"
        ], requireAll = false)
        fun setSrcCompat(
                imageView: ImageView,
                url: String?,
                useBlur: Boolean
        ) {
            val requestBuilder = Glide.with(imageView).load(url)

            if (useBlur) {
                requestBuilder.apply(RequestOptions.bitmapTransform(BlurTransformation()))
            }

            requestBuilder.into(imageView)
        }

        @JvmStatic
        @BindingAdapter("android:onEditorAction")
        fun setOnEditorAction(textView: TextView, onEditorAction: Action) {
            textView.setOnEditorActionListener { _, _, event ->
                if (event?.action != KeyEvent.ACTION_DOWN) {
                    false
                }

                onEditorAction.run()

                true
            }
        }

        @JvmStatic
        @BindingAdapter("android:selected")
        fun setSelected(view: View, selected: Boolean) {
            view.isSelected = selected
        }
    }
}