package com.teamnexters.mosaic.ui.widget

import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.teamnexters.mosaic.R



internal class LoadingDialogFragment : DialogFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LoadingDialogFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(dialog) {
            setContentView(R.layout.dialog_progress)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

}