package com.teamnexters.mosaic.di

import com.google.gson.Gson
import com.teamnexters.mosaic.BuildConfig
import com.teamnexters.mosaic.base.GlobalChannelApi
import com.teamnexters.mosaic.data.remote.MosaicApi
import com.teamnexters.mosaic.network.MosaicCallAdapterFactory
import com.teamnexters.mosaic.network.MosaicRxJava2Transformer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class RetrofitModule {

    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideMosaicRxJavaTransformerFactory(): MosaicRxJava2Transformer.Factory {
        return object : MosaicRxJava2Transformer.Factory {
            override fun <T> newInstance(globalChannelApi: GlobalChannelApi): MosaicRxJava2Transformer<T> {
                return MosaicRxJava2Transformer(globalChannelApi)
            }
        }
    }

    @Provides
    fun provideMosaicRxJavaCallAdapterFactory(
            mosaicTransformerFactory: MosaicRxJava2Transformer.Factory,
            globalChannelApi: GlobalChannelApi

    ): CallAdapter.Factory {
        return MosaicCallAdapterFactory(
                RxJava2CallAdapterFactory.create(),
                mosaicTransformerFactory,
                globalChannelApi
        )
    }

    @Provides
    @Singleton
    fun provideMosaicRetrofit(
            client: OkHttpClient,
            converterFactory: Converter.Factory,
            callAdapterFactory: CallAdapter.Factory

    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideMosaicApi(retrofit: Retrofit): MosaicApi {
        return retrofit.create(MosaicApi::class.java)
    }

}