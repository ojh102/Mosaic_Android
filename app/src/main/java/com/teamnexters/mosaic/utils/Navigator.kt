package com.teamnexters.mosaic.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.teamnexters.mosaic.data.local.model.Keyword
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.ui.Screen
import com.teamnexters.mosaic.ui.detail.DetailActivity
import com.teamnexters.mosaic.ui.detail.DetailActivity.Companion.DETAIL_INTENT_KEY
import com.teamnexters.mosaic.ui.filter.FilterActivity
import com.teamnexters.mosaic.ui.login.LoginActivity
import com.teamnexters.mosaic.ui.main.MainActivity
import com.teamnexters.mosaic.ui.mypage.MyPageActivity
import com.teamnexters.mosaic.ui.result.ResultActivity
import com.teamnexters.mosaic.ui.search.SearchActivity
import com.teamnexters.mosaic.ui.write.WriteActivity
import com.teamnexters.mosaic.utils.extension.startActivityWithTransition

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
        fun navigateToMypage(context: Context) {
            context.startActivity(
                    Intent(context, MyPageActivity::class.java)
            )
        }

        @JvmStatic
        fun navigateToFilter(context: Context) {
            if(context is Activity) {
                context.startActivityForResult(
                        Intent(context, FilterActivity::class.java),
                        FilterActivity.REQUEST_FILTER
                )
            }
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
        fun navigationToResult(context: Context, keyword: Keyword, fromScreen: Screen) {
            context.startActivity(Intent(context, ResultActivity::class.java).apply {
                putExtra(ResultActivity.KEY_TITLE, keyword.keyword)
                putExtra(ResultActivity.KEY_FROM_SCREEN, fromScreen)
            })
        }

        @JvmStatic
        fun navigateToLogin(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

            context.startActivity(intent)
        }

        @JvmStatic
        fun navigateToDetail(context: Context, scriptResponse: ScriptResponse) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(DETAIL_INTENT_KEY, scriptResponse)
            }

            context.startActivity(intent)
        }
    }
}