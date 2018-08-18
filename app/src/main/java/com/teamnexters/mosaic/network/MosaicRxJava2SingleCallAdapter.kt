package com.teamnexters.mosaic.network

import com.teamnexters.mosaic.base.GlobalChannelApi
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type


internal class MosaicRxJava2SingleCallAdapter<T>(
        private val delegate: CallAdapter<T, Single<T>>,
        private val transformerFactory: MosaicRxJava2Transformer.Factory,
        private val globalChannelApi: GlobalChannelApi

) : CallAdapter<T, Single<T>> {

    override fun responseType(): Type {
        return delegate.responseType()
    }

    override fun adapt(call: Call<T>): Single<T> {
        return delegate.adapt(call).compose(transformerFactory.newInstance(globalChannelApi))
    }

}