package com.teamnexters.mosaic.ui.result

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.ui.main.CardLooknFeel
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


internal class ResultViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi

) : BaseViewModel() {

    private val titleRelay = BehaviorRelay.createDefault("")
    private val fromScreenRelay = BehaviorRelay.create<FromScreen>()

    private val backRelay = PublishRelay.create<Unit>()
    private val closeRelay = PublishRelay.create<Unit>()

    init {
        bind(
                intent().subscribeBy(
                        onNext = {
                            titleRelay.accept(it.getStringExtra(ResultActivity.KEY_TITLE))
                            fromScreenRelay.accept(it.getSerializableExtra(ResultActivity.KEY_FROM_SCREEN) as FromScreen)
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

    fun bindResultList(): Observable<List<CardLooknFeel>> {
        return fromScreenRelay.flatMap {
            when(it) {
                FromScreen.Search -> remoteRepository.fetchResultListFromSearch()
                FromScreen.Scrap -> remoteRepository.fetchResultListFromScrap()
                FromScreen.Written -> remoteRepository.fetchResultListFromWritten()
            }
        }
    }

}