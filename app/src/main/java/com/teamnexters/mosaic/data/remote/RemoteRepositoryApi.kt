package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.*
import io.reactivex.Observable
import io.reactivex.Single

internal interface RemoteRepositoryApi {
    fun fetchEmailSend(email: String): Observable<EmailSendResponse>
    fun fetchTokenInfo(authKey: String, uuid: String): Observable<TokenInfoResponse>
    fun fetchScriptList(vararg categories: String): Observable<List<ScriptResponse>>
    fun fetchRelpies(scriptUuid: String): Observable<List<ReplyResponse>>
    fun fetchFilterList(): Observable<List<CategoryResponse>>
    fun fetchResultListFromSearch(keyword: String): Observable<List<ScriptResponse>>
    fun fetchResultListFromWritten(): Observable<List<ScriptResponse>>
    fun fetchResultListFromScrap(): Observable<List<ScriptResponse>>
    fun fetchMyPage(): Observable<WriterResponse>
}
