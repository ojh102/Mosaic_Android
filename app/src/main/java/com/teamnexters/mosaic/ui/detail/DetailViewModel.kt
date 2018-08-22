package com.teamnexters.mosaic.ui.detail

import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.data.remote.model.ReplyResponse
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import io.reactivex.Observable
import java.io.File
import javax.inject.Inject

internal class DetailViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi
) : BaseViewModel()  {

    fun getReplies(scriptUuid: String): Observable<List<ReplyResponse>> {
        return remoteRepository.fetchRelpies(scriptUuid)
    }

    fun addReplies(content: String, imgFile: File?, scriptUuid: String, upperReplyUuid: String): Observable<ReplyResponse> {
        return remoteRepository.fetchAddReply(content, imgFile, scriptUuid)
    }

    fun scrap(scriptUuid: String): Observable<ScriptResponse> {
        return remoteRepository.scrap(scriptUuid)
    }

}