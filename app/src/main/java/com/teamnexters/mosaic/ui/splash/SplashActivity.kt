package com.teamnexters.mosaic.ui.splash

import android.os.Bundle
import com.teamnexters.mosaic.R
import com.teamnexters.mosaic.base.BaseActivity
import com.teamnexters.mosaic.databinding.ActivitySplashBinding
import com.teamnexters.mosaic.utils.Navigator
import io.reactivex.rxkotlin.subscribeBy

internal class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun getLayoutRes() = R.layout.activity_splash

    override fun getViewModelClass() = SplashViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind(
                viewModel.text()
                        .subscribeBy(
                                onSuccess = {
                                    binding.text = it

                                    Navigator.navigateToMain(this)
                                    finish()
                                }
                        )
        )
    }
}
