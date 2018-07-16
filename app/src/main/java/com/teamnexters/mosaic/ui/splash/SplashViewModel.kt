package com.teamnexters.mosaic.ui.splash

import com.teamnexters.mosaic.base.BaseViewModel
import io.reactivex.Single
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {
    fun text(): Single<String> {
        return Single.just("test")
    }
}