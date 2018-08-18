package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.ui.common.theme.ThemeData
import com.teamnexters.mosaic.ui.mypage.MyPageData
import io.reactivex.Observable

internal interface RemoteRepositoryApi {
    fun fetchMainCardList(): Observable<List<ScriptResponse>>
    fun fetchFilterList(): Observable<List<ThemeData>>
    fun fetchResultListFromSearch(): Observable<List<ScriptResponse>>
    fun fetchResultListFromWritten(): Observable<List<ScriptResponse>>
    fun fetchResultListFromScrap(): Observable<List<ScriptResponse>>
    fun fetchMyPage(): Observable<MyPageData>
}
