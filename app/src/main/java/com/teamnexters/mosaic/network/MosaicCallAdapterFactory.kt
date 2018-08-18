package com.teamnexters.mosaic.network

import io.reactivex.Single
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type
import javax.inject.Inject


internal class MosaicCallAdapterFactory @Inject constructor(
        private val delegate: RxJava2CallAdapterFactory,
        private val transformerFactory: MosaicRxJava2Transformer.Factory

) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val defaultAdapter = delegate.get(returnType, annotations, retrofit)

        val rawType = CallAdapter.Factory.getRawType(returnType)

        @Suppress("unchecked_cast")
        return when(rawType) {
            Single::class.java -> MosaicRxJava2SingleCallAdapter(defaultAdapter as CallAdapter<Any, Single<Any>>, transformerFactory)
            else -> defaultAdapter
        }
    }

}