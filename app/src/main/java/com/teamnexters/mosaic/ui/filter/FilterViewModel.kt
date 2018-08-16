package com.teamnexters.mosaic.ui.filter

import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

internal class FilterViewModel @Inject constructor() : BaseViewModel() {

    private val clickCloseRelay = PublishRelay.create<Unit>()

    fun onClickClose() {
        clickCloseRelay.accept(Unit)
    }

    fun bindClickClose(): Observable<Unit> {
        return clickCloseRelay
    }
}