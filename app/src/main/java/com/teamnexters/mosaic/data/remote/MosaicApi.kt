package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

internal interface MosaicApi {
    @POST("/login/email")
    fun sendEmail(
            @Field("email") email: String
    ): Call<EmailSendResponse>

    @GET("/apis/scripts")
    fun fetchScripts(@Query("categories") vararg categories: String): Single<ResponseEnvelope<List<ScriptResponse>>>

    @GET("/apis/scripts/mine")
    fun fetchMyScripts(): Single<ResponseEnvelope<List<ScriptResponse>>>

    @GET("/apis/scraps")
    fun fetchScrapesScripts(): Single<ResponseEnvelope<List<ScriptResponse>>>

    @GET("/apis/scripts/search")
    fun fetchSearchedScripts(@Query("keyword") keyword: String): Single<ResponseEnvelope<List<ScriptResponse>>>

    @GET("/apis/me")
    fun fetchMyPageInfo(): Single<ResponseEnvelope<WriterResponse>>

    @GET("/categories")
    fun fetchCategories(): Single<ResponseEnvelope<List<CategoryResponse>>>

    @FormUrlEncoded
    @POST("/apis/scrap")
    fun scarp(@Field("scriptUuid") scriptUuid: String): Single<ResponseEnvelope<ScriptResponse>>
}
