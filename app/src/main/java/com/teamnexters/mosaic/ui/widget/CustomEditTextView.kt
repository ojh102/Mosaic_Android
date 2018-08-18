package com.teamnexters.mosaic.ui.widget

import android.content.Context
import android.support.text.emoji.widget.EmojiTextViewHelper
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.text.InputFilter
import android.util.AttributeSet


class CustomEditTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var mEmojiTextViewHelper: EmojiTextViewHelper? = null

    /**
     * Returns the [EmojiTextViewHelper] for this TextView.
     *
     * This property can be called from super constructors through [#setFilters] or [#setAllCaps].
     */
    private val emojiTextViewHelper: EmojiTextViewHelper
        get() {
            if (mEmojiTextViewHelper == null) {
                mEmojiTextViewHelper = EmojiTextViewHelper(this)
            }
            return mEmojiTextViewHelper as EmojiTextViewHelper
        }

    init {
        emojiTextViewHelper.updateTransformationMethod()
    }

    override fun setFilters(filters: Array<InputFilter>) {
        super.setFilters(emojiTextViewHelper.getFilters(filters))
    }

    override fun setAllCaps(allCaps: Boolean) {
        super.setAllCaps(allCaps)
        emojiTextViewHelper.setAllCaps(allCaps)
    }

}