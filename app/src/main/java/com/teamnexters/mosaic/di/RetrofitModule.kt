package com.teamnexters.mosaic.di

import com.google.gson.Gson
import com.teamnexters.mosaic.BuildConfig
import com.teamnexters.mosaic.data.remote.MosaicApi
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
class RetrofitModule {

    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRxJavaCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideCapsuleRetrofit(
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