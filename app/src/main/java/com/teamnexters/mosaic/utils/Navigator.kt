package com.teamnexters.mosaic.utils

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.teamnexters.mosaic.ui.main.MainActivity
import com.teamnexters.mosaic.ui.search.keyword.SearchActivity
import com.teamnexters.mosaic.ui.write.WriteActivity
import com.teamnexters.mosaic.utils.extension.startActivityWithTransition

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
        fun navigateToSearch(activity: AppCompatActivity, vararg views: View) {
            val intent = Intent(activity, SearchActivity::class.java)

            activity.startActivityWithTransition(intent, *views)
        }

        @JvmStatic
        fun navigationToSearchResult(context: Context) {

        }
    }
}