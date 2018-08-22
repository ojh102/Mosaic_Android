package com.teamnexters.mosaic.ui.main

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.teamnexters.mosaic.base.BaseViewModel
import com.teamnexters.mosaic.base.GlobalChannelApi
import com.teamnexters.mosaic.data.remote.RemoteRepositoryApi
import com.teamnexters.mosaic.data.remote.model.CategoryResponse
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import com.teamnexters.mosaic.ui.filter.FilterActivity
import com.teamnexters.mosaic.utils.extension.subscribeOf
import io.reactivex.Observable
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
        private val remoteRepository: RemoteRepositoryApi,
        private val globalChannelApi: GlobalChannelApi

) : BaseViewModel() {

    private val clickSearchRelay = PublishRelay.create<Unit>()

    private val filterRelay = BehaviorRelay.createDefault(listOf<String>())

    init {
        bind(
                activityResult()
                        .filter {
                            val requestCode = it.first

                            requestCode == FilterActivity.REQUEST_FILTER
                        }
                        .map {
                            val intent = it.third

                            intent!!.getParcelableArrayListExtra<CategoryResponse>(FilterActivity.KEY_FILTER)
                                    .map { category ->
                                        category.uuid
                                    }
                        }
                        .subscribeOf(
                                onNext = {
                                    filterRelay.accept(it)
                                }
                        )
        )
    }

    fun fetchScriptList(): Observable<List<ScriptResponse>> {
        return remoteRepository.fetchScriptList(*filterRelay.value.toTypedArray())
    }

    fun onClickSearch() {
        clickSearchRelay.accept(Unit)
    }

    fun bindClickSearch(): Observable<Unit> {
        return clickSearchRelay
    }

    fun scarp(scriptUuid: String): Observable<ScriptResponse> {
        return remoteRepository.scrap(scriptUuid)
    }

    fun bindScrap(): Observable<Pair<String, Boolean>> {
        return globalChannelApi.bindScrapCard()
    }

    fun bindDelete() = globalChannelApi.bindDeleteCard()

}