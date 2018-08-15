package com.teamnexters.mosaic.ui.main

import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepository
import io.reactivex.Observable
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val remoteRepository: RemoteRepository

) : BaseViewModel() {

    private val clickSearchRelay = PublishRelay.create<Unit>()

    fun getCards(): Observable<List<CardLooknFeel>> {
        return remoteRepository.getCards()
    }

    fun onClickSearch() {
        clickSearchRelay.accept(Unit)
    }

    fun bindClickSearch(): Observable<Unit> {
        return clickSearchRelay
    }

}