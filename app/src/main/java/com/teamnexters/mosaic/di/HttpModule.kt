package com.teamnexters.mosaic.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.teamnexters.mosaic.BuildConfig
import com.teamnexters.mosaic.data.local.MosaicSharedPreferenceManager
import com.teamnexters.mosaic.di.qualifier.HttpLogging
import com.teamnexters.mosaic.di.qualifier.RequestIntercetor
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

    @Module
    class ProvideModule {
        @Provides
        @HttpLogging
        fun provideHttpLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
        }

        @Provides
        @RequestIntercetor
        fun provideRequestInterceptor(mosaicSharedPreferenceManager: MosaicSharedPreferenceManager): Interceptor {
            return Interceptor { chain ->
                val fakeToken = "eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAACXLTQqAIBBA4bvMWqFRx9Lb-AuulFQIortntHmLD94NpXewUMOoLZ0ZGBQ3wCJJRbtANAzS1X7QdNAHc5a4HuM0BgqaZyUDVzEi9xlXNvTGySicJnheVH-IIGIAAAA.MOGRdwI1rh2WRmcyGPge0njtikDhbQgFW1vjCxUUXeo"

                var token = mosaicSharedPreferenceManager.getString(MosaicSharedPreferenceManager.TOKEN)

                token = fakeToken

                val newRequest = chain.request().newBuilder()

                if(token.isNotBlank()) {
                    newRequest
                            .addHeader("Authorization", "Bearer $token")
                }

                chain.proceed(newRequest.build())
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
                @HttpLogging httpLoggingInterceptor: Interceptor,
                @RequestIntercetor requestIntercetor: Interceptor
        ): OkHttpClient {

            val okHttpClientBuilder = OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(requestIntercetor)
                    .addNetworkInterceptor(httpLoggingInterceptor)

            if(BuildConfig.DEBUG) {
                okHttpClientBuilder.addNetworkInterceptor(StethoInterceptor())
            }

            return okHttpClientBuilder.build()
        }
    }
}