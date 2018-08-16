package com.teamnexters.mosaic.ui.scheme

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.ui.login.LoginActivity
import com.teamnexters.mosaic.utils.Navigator

class SchemeProcessNoUIActivity : AppCompatActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        startProcess(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        startProcess(intent)
    }

    fun startProcess(intent: Intent?) {
        val uri = intent?.data
        val host = uri?.host

        if (host.equals(resources.getString(R.string.email_confirm_host))) {
            uri?.getQueryParameter(resources.getString(R.string.email_confirm_code))?.let {
                if (TextUtils.isEmpty(it) == false) {
                    //서버에 이메일을 보내달라고 요청!

                    val schemeIntent = Intent(this, LoginActivity::class.java)
                    schemeIntent.putExtra(resources.getString(R.string.email_confirm_code), it)


                    Navigator.navigateWithIntent(this, schemeIntent)
                }
            }
        }
    }
}