package com.teamnexters.mosaic.ui.widget

import android.content.Context
import android.support.text.emoji.widget.EmojiAppCompatEditText
import android.text.*
import android.util.AttributeSet
import android.util.Log
import com.teamnexters.mosaic.data.remote.model.ReplyResponse

class DetailWriteEditText @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = android.support.v7.appcompat.R.attr.editTextStyle) : EmojiAppCompatEditText(context, attrs, defStyleAttr) {

    var isRereplyMode = false
    internal var rereplyDetailData: ReplyResponse? = null

    fun isRereplyEmpty() : Boolean {
        rereplyDetailData?.writer?.nick?.length.let {
            if (it == null) return TextUtils.isEmpty(text) else return text.length <= it + 1
        }
    }

    init {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(isRereplyMode){
                    rereplyDetailData?.writer?.nick?.length.let {
                        if (it != null) {
                            if (text.length < it + 1) {
                                setRereplyMode(null)
                            }
                        }else{

                        }
                    }
                }
            }
        })
    }

    fun getTextRereply(): Editable {
        var editable : Editable = super.getText()

        if(isRereplyMode){
            rereplyDetailData?.writer?.nick?.length.let {
                if (it != null) editable = SpannableStringBuilder(editable.subSequence(it + 1, editable.length))
            }
        }

        return editable
    }

    internal fun setRereplyMode(rereplyResponse: ReplyResponse?){
        rereplyDetailData = rereplyResponse

        if(rereplyResponse != null){
            isRereplyMode = true
            setText(Html.fromHtml("<font color=#ff573d>${rereplyResponse.writer.nick}</font>  "))
            setSelection(text.length)
        } else{
            isRereplyMode = false
            setText("")
            setSelection(text.length)
        }
    }
}