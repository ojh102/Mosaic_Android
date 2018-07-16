package com.teamnexters.mosaic.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.teamnexters.mosaic.BuildConfig
import com.teamnexters.mosaic.di.qualifier.HttpLogging
import com.teamnexters.mosaic.di.qualifier.Stetho
import dagger.Binds
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [HttpModule.ProvideModule::class])
interface HttpModule {
    companion object {
        const val TIMEOUT = 10L
    }

    @Binds
    @Stetho
    fun bindStethoInterceptor(stethoInterceptor: StethoInterceptor): Interceptor

    @Module
    class ProvideModule {
        @Provides
        @HttpLogging
        fun provideHttpLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
        }

        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder()
                    .setPrettyPrinting()
                    .create()
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
                @Stetho stethoInterceptor: Lazy<Interceptor>,
                @HttpLogging httpLoggingInterceptor: Interceptor

        ): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .addNetworkInterceptor(httpLoggingInterceptor)

            if (BuildConfig.DEBUG) {
                okHttpClientBuilder.addNetworkInterceptor(stethoInterceptor.get())
            }

            return okHttpClientBuilder.build()
        }
    }
}