package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.ui.common.theme.ThemeData
import com.teamnexters.mosaic.ui.main.CardLooknFeel
import com.teamnexters.mosaic.ui.mypage.MyPageData
import io.reactivex.Observable

internal interface RemoteRepositoryApi {
    fun fetchMainCardList(): Observable<List<CardLooknFeel>>
    fun fetchFilterList(): Observable<List<ThemeData>>
    fun fetchResultListFromSearch(): Observable<List<CardLooknFeel>>
    fun fetchResultListFromWritten(): Observable<List<CardLooknFeel>>
    fun fetchResultListFromScrap(): Observable<List<CardLooknFeel>>
    fun fetchMyPage(): Observable<MyPageData>
}
