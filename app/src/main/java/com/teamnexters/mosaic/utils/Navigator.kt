package com.teamnexters.mosaic.utils

import android.content.Context
import android.content.Intent
import com.teamnexters.mosaic.ui.main.MainActivity
import com.teamnexters.mosaic.ui.write.WriteActivity

class Navigator {
    companion object {
        @JvmStatic
        fun navigateToMain(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }

        @JvmStatic
        fun navigateToWrite(context: Context) {
            context.startActivity(Intent(context, WriteActivity::class.java))
        }
    }
}