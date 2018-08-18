package com.teamnexters.mosaic.base

import io.reactivex.Observable


internal interface GlobalChannelApi {
    fun showLoading()
    fun hideLoading()

    fun bindShowLoading(): Observable<Unit>
    fun bindHideLoading(): Observable<Unit>

    fun deleteCard(uuid: String)
    fun scrapCard(uuid: String, scraped: Boolean)

    fun bindDeleteCard(): Observable<String>
    fun bindScrapCard(): Observable<Pair<String, Boolean>>
}