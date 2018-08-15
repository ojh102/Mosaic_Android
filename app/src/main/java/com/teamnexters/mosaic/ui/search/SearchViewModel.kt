package com.teamnexters.mosaic.ui.search

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject


internal class SearchViewModel @Inject constructor(

) : BaseViewModel() {

    private val clickCancelRelay = PublishRelay.create<Unit>()
    private val clickKeywordCancelRelay = PublishRelay.create<Unit>()
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

    fun acceptKeyword(keword: String) {
        keywordRelay.accept(keword)
    }

    fun bindKeyword(): Observable<String> {
        return keywordRelay
    }

}