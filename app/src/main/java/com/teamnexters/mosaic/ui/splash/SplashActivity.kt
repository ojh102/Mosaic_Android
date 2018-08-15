package com.teamnexters.mosaic.ui.splash

import android.os.Bundle
import android.os.Handler
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivitySplashBinding
import com.teamnexters.mosaic.utils.Navigator
import io.reactivex.rxkotlin.subscribeBy

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    val mRemainSecond : Long = 1000

    override fun getLayoutRes() = R.layout.activity_splash

    override fun getViewModelClass() = SplashViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLogin()
    }

    fun checkLogin() {
        Handler().let {
            it.postDelayed(Runnable {
                if(true) Navigator.navigateToLogin(this) else Navigator.navigateToMain(this)

                finish()
            }, mRemainSecond)
        }
    }
}
