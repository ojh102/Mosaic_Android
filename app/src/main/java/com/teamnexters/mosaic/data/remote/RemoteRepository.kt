package com.teamnexters.mosaic.data.remote

import android.content.res.Resources
import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.data.remote.model.WriterResponse
import com.teamnexters.mosaic.utils.extension.validate
import io.reactivex.Observable
import javax.inject.Inject

internal class RemoteRepository @Inject constructor(
        private val mosaicApi: MosaicApi,
        private val resource: Resources

) : RemoteRepositoryApi {

    override fun fetchScriptList(vararg categories: String): Observable<List<ScriptResponse>> {
        return mosaicApi.fetchScripts("", *categories)
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

}