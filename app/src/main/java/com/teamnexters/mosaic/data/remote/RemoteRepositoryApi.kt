package com.teamnexters.mosaic.data.remote

import android.net.Uri
import com.teamnexters.mosaic.data.remote.model.*
import io.reactivex.Observable
import io.reactivex.Single
import java.io.File

internal interface RemoteRepositoryApi {
    fun fetchEmailSend(email: String): Observable<EmailSendResponse>
    fun fetchTokenInfo(authKey: String, uuid: String): Observable<TokenInfoResponse>
    fun fetchScriptList(vararg categories: String): Observable<List<ScriptResponse>>
    fun fetchRelpies(scriptUuid: String): Observable<List<ReplyResponse>>
    fun fetchAddReply(content: String, imgFile: File?, scriptUuid: String) :Observable<ReplyResponse>
    fun fetchFilterList(): Observable<List<CategoryResponse>>
    fun fetchResultListFromSearch(keyword: String): Observable<List<ScriptResponse>>
    fun fetchResultListFromWritten(): Observable<List<ScriptResponse>>
    fun fetchResultListFromScrap(): Observable<List<ScriptResponse>>
    fun fetchMyPage(): Observable<WriterResponse>
    fun scrap(scriptUuid: String): Observable<ScriptResponse>
    fun saveScript(categoryUuid: String, content: String, imgFile: List<File> = listOf()): Observable<ScriptResponse>
}
