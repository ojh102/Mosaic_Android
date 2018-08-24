package com.teamnexters.mosaic.ui.splash

import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.data.remote.model.TokenInfoResponse
import com.teamnexters.mosaic.data.remote.model.WriterResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

internal class SplashViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi
) : BaseViewModel()  {

    //스킴을 통해 받은 코드가 맞는지 확인하는 로직
    fun getTokenInfo(authKey : String, uuid : String) : Observable<TokenInfoResponse>{
        return remoteRepository.fetchTokenInfo(authKey = authKey, uuid = uuid)
    }
}