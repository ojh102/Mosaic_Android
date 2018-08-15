package com.teamnexters.mosaic.utils

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.teamnexters.mosaic.data.local.model.Keyword
import com.teamnexters.mosaic.ui.main.MainActivity
import com.teamnexters.mosaic.ui.search.keyword.SearchActivity
import com.teamnexters.mosaic.ui.search.result.SearchResultActivity
import com.teamnexters.mosaic.ui.write.WriteActivity
import com.teamnexters.mosaic.utils.extension.startActivityWithTransition

internal class Navigator {
    companion object {
        const val KEY_KEYWORD = "keyword"

        @JvmStatic
        fun navigateToMain(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
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
        fun navigationToSearchResult(context: Context, keyword: Keyword) {
            val intent = Intent(context, SearchResultActivity::class.java).apply {
                putExtra(KEY_KEYWORD, keyword.keyword)
            }

            context.startActivity(intent)
        }
    }
}