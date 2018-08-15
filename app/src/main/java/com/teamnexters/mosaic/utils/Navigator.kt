package com.teamnexters.mosaic.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.teamnexters.mosaic.ui.main.MainActivity
import com.teamnexters.mosaic.ui.write.WriteActivity
import com.teamnexters.mosaic.ui.login.LoginActivity


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

        @JvmStatic
        fun navigateToLogin(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }

        @JvmStatic
        fun navigateToInternet(context: Context) {
            context.startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("http://www.naver.com")))
        }

        @JvmStatic
        fun navigateWithIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }
    }
}