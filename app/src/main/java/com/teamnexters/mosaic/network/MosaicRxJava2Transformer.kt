package com.teamnexters.mosaic.network

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer


internal class MosaicRxJava2Transformer<T> : SingleTransformer<T, T> {

    interface Factory {
        fun <T> newInstance(): MosaicRxJava2Transformer<T>
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
    }

}