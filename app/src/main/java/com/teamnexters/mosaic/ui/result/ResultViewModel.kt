package com.teamnexters.mosaic.ui.result

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.ui.Screen
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


internal class ResultViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi

) : BaseViewModel() {

    private val titleRelay = BehaviorRelay.createDefault("")
    private val fromScreenRelay = BehaviorRelay.create<Screen>()

    private val backRelay = PublishRelay.create<Unit>()
    private val closeRelay = PublishRelay.create<Unit>()

    init {
        bind(
                intent().subscribeBy(
                        onNext = {
                            titleRelay.accept(it.getStringExtra(ResultActivity.KEY_TITLE))
                            fromScreenRelay.accept(it.getSerializableExtra(ResultActivity.KEY_FROM_SCREEN) as Screen)
                        }
                )
        )
    }

    fun onClickBack() {
        backRelay.accept(Unit)
    }

    fun onClickClose() {
        closeRelay.accept(Unit)
    }

    fun bindClickBack(): Observable<Unit> {
        return backRelay
    }

    fun bindClickClose(): Observable<Unit> {
        return closeRelay
    }

    fun bindTitle(): Observable<String> {
        return titleRelay
    }

    fun bindFromScreen(): Observable<Screen> {
        return fromScreenRelay
    }

    fun bindResultList(): Observable<List<ScriptResponse>> {
        return fromScreenRelay.flatMap {
            when(it) {
                Screen.Search -> remoteRepository.fetchResultListFromSearch(titleRelay.value)
                Screen.Scrap -> remoteRepository.fetchResultListFromScrap()
                Screen.Written -> remoteRepository.fetchResultListFromWritten()
                else -> throw RuntimeException("있을리가 없음ㅋ")
            }
        }
    }

}