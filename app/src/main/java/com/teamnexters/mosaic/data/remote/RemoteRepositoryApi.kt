package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.ui.main.CardLooknFeel
import io.reactivex.Observable

interface RemoteRepositoryApi {
    fun fetchMainCardList(): Observable<List<CardLooknFeel>>
    fun fetchResultListFromSearch(): Observable<List<CardLooknFeel>>
    fun fetchResultListFromWritten(): Observable<List<CardLooknFeel>>
    fun fetchResultListFromScrap(): Observable<List<CardLooknFeel>>
}
