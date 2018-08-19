package com.teamnexters.mosaic.ui.filter

import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepository
import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import io.reactivex.Observable
import javax.inject.Inject

internal class FilterViewModel @Inject constructor(
        private val remoteRepository: RemoteRepository

) : BaseViewModel() {

    private val clickCloseRelay = PublishRelay.create<Unit>()

    fun onClickClose() {
        clickCloseRelay.accept(Unit)
    }

    fun bindClickClose(): Observable<Unit> {
        return clickCloseRelay
    }

    fun fetchFilterList(): Observable<List<CategoryResponse>> {
        return remoteRepository.fetchFilterList()
    }
}