package com.teamnexters.mosaic.ui.search.keyword

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.local.LocalRepositoryApi
import com.teamnexters.mosaic.data.local.model.Keyword
import io.reactivex.Observable
import javax.inject.Inject


internal class SearchViewModel @Inject constructor(
        private val localRepository: LocalRepositoryApi

) : BaseViewModel() {

    private val clickCancelRelay = PublishRelay.create<Unit>()
    private val clickKeywordCancelRelay = PublishRelay.create<Unit>()
    private val clickSearchRelay = PublishRelay.create<Unit>()
    private val keywordRelay = BehaviorRelay.createDefault("")

    fun onClickCancel() {
        clickCancelRelay.accept(Unit)
    }

    fun onClickKeywordCancel() {
        clickKeywordCancelRelay.accept(Unit)
    }

    fun bindClickCancel(): Observable<Unit> {
        return clickCancelRelay
    }

    fun bindClickKewordCancel(): Observable<Unit> {
        return clickKeywordCancelRelay
    }

    fun acceptKeyword(keyword: String) {
        keywordRelay.accept(keyword)
    }

    fun bindKeyword(): Observable<String> {
        return keywordRelay
    }

    fun onClickSearch() {
        clickSearchRelay.accept(Unit)
    }

    fun bindClickSearch(): Observable<Unit> {
        return clickSearchRelay
    }

    fun bindSearchKeywordList(): Observable<List<Keyword>> {
        return localRepository.getKeywordList()
    }

    fun addKeyword(keyword: Keyword) {
        localRepository.addKeyword(keyword)
    }

}