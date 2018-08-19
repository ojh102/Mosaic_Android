package com.teamnexters.mosaic.ui.mypage

import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.data.remote.model.WriterResponse
import io.reactivex.Observable
import javax.inject.Inject

internal class MyPageViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi

) : BaseViewModel() {

    private val clickBackRelay = PublishRelay.create<Unit>()

    fun onClickBack() {
        clickBackRelay.accept(Unit)
    }

    fun bindClickBack(): Observable<Unit> {
        return clickBackRelay
    }

    fun fetchMyPage(): Observable<WriterResponse> {
        return remoteRepository.fetchMyPage()
    }

}