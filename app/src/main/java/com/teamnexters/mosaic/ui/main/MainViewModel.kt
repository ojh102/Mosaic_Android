package com.teamnexters.mosaic.ui.main

import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import io.reactivex.Observable
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi

) : BaseViewModel() {

    private val clickSearchRelay = PublishRelay.create<Unit>()

    fun fetchMainCardList(): Observable<List<CardLooknFeel>> {
        return remoteRepository.fetchMainCardList()
    }

    fun onClickSearch() {
        clickSearchRelay.accept(Unit)
    }

    fun bindClickSearch(): Observable<Unit> {
        return clickSearchRelay
    }

}