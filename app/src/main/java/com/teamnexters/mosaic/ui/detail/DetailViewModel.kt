package com.teamnexters.mosaic.ui.detail

import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.data.remote.model.ReplyResponse
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.ui.Screen
import io.reactivex.Observable
import javax.inject.Inject

internal class DetailViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi
) : BaseViewModel()  {

    fun getReplies(scriptUuid: String): Observable<List<ReplyResponse>> {
        return remoteRepository.fetchRelpies(scriptUuid)
    }
}