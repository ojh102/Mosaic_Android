package com.teamnexters.mosaic.ui.splash

import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.data.remote.model.WriterResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

internal class SplashViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi
) : BaseViewModel()  {

    fun isLogin(): Observable<WriterResponse> {
        return remoteRepository.fetchMyPage()
    }
}