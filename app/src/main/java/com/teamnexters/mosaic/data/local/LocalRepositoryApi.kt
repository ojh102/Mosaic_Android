package com.teamnexters.mosaic.data.local

import com.teamnexters.mosaic.data.local.model.Keyword
import io.reactivex.Observable


internal interface LocalRepositoryApi {
    fun getKeywordList(): Observable<List<Keyword>>
    fun addKeyword(keyword: String)
}