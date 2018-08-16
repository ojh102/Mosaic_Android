package com.teamnexters.mosaic.utils

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.teamnexters.mosaic.data.local.model.Keyword
import android.net.Uri
import com.teamnexters.mosaic.ui.main.MainActivity
import com.teamnexters.mosaic.ui.result.FromScreen
import com.teamnexters.mosaic.ui.result.ResultActivity
import com.teamnexters.mosaic.ui.search.SearchActivity
import com.teamnexters.mosaic.ui.write.WriteActivity
import com.teamnexters.mosaic.utils.extension.startActivityWithTransition
import com.teamnexters.mosaic.ui.login.LoginActivity

internal class Navigator {
    companion object {
        @JvmStatic
        fun navigateToMain(context: Context, isClear: Boolean = false) {
            context.startActivity(
                    Intent(context, MainActivity::class.java).apply {
                        if(isClear) {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        }
                    }
            )
        }

        @JvmStatic
        fun navigateToWrite(context: Context) {
            context.startActivity(Intent(context, WriteActivity::class.java))
        }

        @JvmStatic
        fun navigateToSearch(activity: AppCompatActivity, vararg views: View) {
            val intent = Intent(activity, SearchActivity::class.java)

            activity.startActivityWithTransition(intent, *views)
        }

        @JvmStatic
        fun navigationToSearchResult(context: Context, keyword: Keyword, fromScreen: FromScreen) {
            val intent = Intent(context, ResultActivity::class.java).apply {
                putExtra(ResultActivity.KEY_TITLE, keyword.keyword)
                putExtra(ResultActivity.KEY_FROM_SCREEN, fromScreen)
            }
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