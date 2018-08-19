package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.data.remote.model.WriterResponse
import io.reactivex.Observable

internal interface RemoteRepositoryApi {
    fun fetchMainCardList(): Observable<List<ScriptResponse>>
    fun fetchFilterList(): Observable<List<CategoryResponse>>
    fun fetchResultListFromSearch(keyword: String): Observable<List<ScriptResponse>>
    fun fetchResultListFromWritten(): Observable<List<ScriptResponse>>
    fun fetchResultListFromScrap(): Observable<List<ScriptResponse>>
    fun fetchMyPage(): Observable<WriterResponse>
}
