package com.teamnexters.mosaic.ui.splash

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.data.local.MosaicSharedPreferenceManager
import com.teamnexters.mosaic.databinding.ActivitySplashBinding
import com.teamnexters.mosaic.utils.Navigator
import com.teamnexters.mosaic.utils.extension.subscribeOf
import com.teamnexters.mosaic.utils.extension.toast
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

internal class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    enum class LoginStatus{
        LOGIN,
        NOT_LOGIN
    }

    @Inject
    lateinit var sharedPreferenceManager: MosaicSharedPreferenceManager

    val mRemainSecond : Long = 1000

    var isLogin = LoginStatus.NOT_LOGIN
    var delayTimeExpired = false

    override fun getLayoutRes() = R.layout.activity_splash

    override fun getViewModelClass() = SplashViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLogin()
    }

    fun checkLogin() {
        getTokenInfo()

        Handler().let {
            it.postDelayed(Runnable {
                when(isLogin){
                    LoginStatus.LOGIN -> {
                        Navigator.navigateToMain(this)
                        finish()
                    }
                    LoginStatus.NOT_LOGIN -> {
                        Navigator.navigateToLogin(this)
                        finish()
                    }
                    else -> {
                        delayTimeExpired = true
                    }
                }
            }, mRemainSecond)
        }
    }


    fun getTokenInfo(){
        val authKey = sharedPreferenceManager.getString(MosaicSharedPreferenceManager.AUTH_KEY,"")
        val uuid = sharedPreferenceManager.getString(MosaicSharedPreferenceManager.UUID,"")

        viewModel.getTokenInfo(authKey, uuid).subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeOf(
                        onNext = {
                            sharedPreferenceManager.setString(MosaicSharedPreferenceManager.TOKEN, it.token)

                            isLogin = LoginStatus.LOGIN

                            if(delayTimeExpired){
                                Navigator.navigateToMain(this)
                                finish()
                            }
                        },
                        onError = {
                            isLogin = LoginStatus.NOT_LOGIN

                            if(delayTimeExpired){
                                Navigator.navigateToLogin(this)
                                finish()
                            }
                        }
                )
    }
}
