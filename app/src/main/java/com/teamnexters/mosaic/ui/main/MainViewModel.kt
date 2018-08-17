package com.teamnexters.mosaic.ui.main

import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.ui.filter.FilterActivity
import com.teamnexters.mosaic.ui.common.theme.ThemeData
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi

) : BaseViewModel() {

    private val clickSearchRelay = PublishRelay.create<Unit>()

    init {
        bind(
                activityResult()
                        .filter {
                            val requestCode = it.first

                            requestCode == FilterActivity.REQUEST_FILTER
                        }
                        .map {
                            val intent = it.third

                            intent!!.getParcelableArrayListExtra<ThemeData>(FilterActivity.KEY_FILTER)
                        }
                        .subscribeBy(
                                onNext = {

                                }
                        )
        )
    }

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