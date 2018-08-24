package com.teamnexters.mosaic.data.remote

import android.content.res.Resources
import com.teamnexters.mosaic.data.remote.model.*
import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.data.remote.model.WriterResponse
import com.teamnexters.mosaic.utils.extension.validate
import io.reactivex.Observable
import io.reactivex.Single
import java.io.File
import javax.inject.Inject

internal class RemoteRepository @Inject constructor(
        private val mosaicApi: MosaicApi

) : RemoteRepositoryApi {

    override fun fetchEmailSend(email: String): Observable<EmailSendResponse> {
        return mosaicApi.fetchSendEmail(email)
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchTokenInfo(authKey: String, uuid: String): Observable<TokenInfoResponse> {
        return mosaicApi.fetchTokenInfo(authKey, uuid)
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchScriptList(vararg categories: String): Observable<List<ScriptResponse>> {
        return mosaicApi.fetchScripts(*categories)
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchFilterList(): Observable<List<CategoryResponse>> {
        return mosaicApi.fetchCategories()
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchResultListFromSearch(keyword: String): Observable<List<ScriptResponse>> {
        return mosaicApi.fetchSearchedScripts(keyword)
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchRelpies(scriptUuid: String) : Observable<List<ReplyResponse>> {
        return mosaicApi.fetchRelpies(scriptUuid)
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchAddReply(content: String, imgFile: File?, scriptUuid: String, upperReplyUuid : String?): Observable<ReplyResponse> {
        return mosaicApi.fetchAddRelpies(content,imgFile,scriptUuid, upperReplyUuid)
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchDeleteScripts(scriptUuid: String) : Observable<ScriptResponse>{
        return mosaicApi.fetchDeleteScripts(scriptUuid)
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchResultListFromWritten(): Observable<List<ScriptResponse>> {
        return mosaicApi.fetchMyScripts()
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchResultListFromScrap(): Observable<List<ScriptResponse>> {
        return mosaicApi.fetchScrapesScripts()
                .map { validate(it) }
                .toObservable()
    }

    override fun fetchMyPage(): Observable<WriterResponse> {
        return mosaicApi.fetchMyPageInfo()
                .map { validate(it) }
                .toObservable()
    }

    override fun scrap(scriptUuid: String): Observable<ScriptResponse> {
        return mosaicApi.scarp(scriptUuid)
                .map { validate(it) }
                .toObservable()
    }
}