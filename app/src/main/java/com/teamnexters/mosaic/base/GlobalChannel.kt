package com.teamnexters.mosaic.base

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import javax.inject.Inject


internal class GlobalChannel @Inject constructor(): GlobalChannelApi {

    private val showLoadingRelay = PublishRelay.create<Unit>().toSerialized()
    private val hideLoadingRelay = PublishRelay.create<Unit>().toSerialized()
    private val deleteCardRealy = PublishRelay.create<String>().toSerialized()
    private val scrapCardRelay = PublishRelay.create<Pair<String, Boolean>>().toSerialized()

    override fun showLoading() {
        showLoadingRelay.accept(Unit)
    }

    override fun hideLoading() {
        hideLoadingRelay.accept(Unit)
    }

    override fun bindShowLoading(): Observable<Unit> {
        return showLoadingRelay
    }

    override fun bindHideLoading(): Observable<Unit> {
        return hideLoadingRelay
    }

    override fun deleteCard(uuid: String) {
        deleteCardRealy.accept(uuid)
    }

    override fun scrapCard(uuid: String, scraped: Boolean) {
        scrapCardRelay.accept(uuid to scraped)
    }

    override fun bindDeleteCard(): Observable<String> {
        return deleteCardRealy
    }

    override fun bindScrapCard(): Observable<Pair<String, Boolean>> {
        return scrapCardRelay
    }

}