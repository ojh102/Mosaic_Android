package com.teamnexters.mosaic.ui.login

import com.jakewharton.rxrelay2.BehaviorRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.data.remote.model.EmailSendResponse
import com.teamnexters.mosaic.data.remote.model.TokenInfoResponse
import io.reactivex.Observable
import javax.inject.Inject

internal class LoginViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi
) : BaseViewModel()  {

    //이메일 정보를 서버로 보내는 로직
    fun sendEmailInfo(email : String) : Observable<EmailSendResponse> {
        return remoteRepository.fetchEmailSend(email = email)
    }

    //스킴을 통해 받은 코드가 맞는지 확인하는 로직
    fun getTokenInfo(authKey : String, uuid : String) : Observable<TokenInfoResponse>{
        return remoteRepository.fetchTokenInfo(authKey = authKey, uuid = uuid)
    }
}