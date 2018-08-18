package com.teamnexters.mosaic.network

import com.teamnexters.mosaic.base.GlobalChannelApi
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer


internal class MosaicRxJava2Transformer<T> (
        private val globalChannelApi: GlobalChannelApi

): SingleTransformer<T, T> {

    interface Factory {
        fun <T> newInstance(globalChannelApi: GlobalChannelApi): MosaicRxJava2Transformer<T>
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        globalChannelApi.showLoading()

        return upstream.doAfterTerminate {
            globalChannelApi.hideLoading()
        }
    }

}