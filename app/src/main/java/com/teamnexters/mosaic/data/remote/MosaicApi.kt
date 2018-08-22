package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.*
import io.reactivex.Single
import retrofit2.http.*
import java.io.File

internal interface MosaicApi {
    @FormUrlEncoded
    @POST("/login/email")
    fun fetchSendEmail(@Field("email") email: String): Single<ResponseEnvelope<EmailSendResponse>>

    @FormUrlEncoded
    @POST("/login/token")
    fun fetchTokenInfo(@Field("authKey") authKey: String, @Field("uuid") uuid: String): Single<ResponseEnvelope<TokenInfoResponse>>

    @GET("/apis/scripts")
    fun fetchScripts(@Query("categories") vararg categories: String): Single<ResponseEnvelope<List<ScriptResponse>>>

    @GET("/apis/replies")
    fun fetchRelpies(@Query("scriptUuid") scriptUuid: String): Single<ResponseEnvelope<List<ReplyResponse>>>

    @FormUrlEncoded
    @POST("/apis/reply")
    fun fetchAddRelpies(@Field("content") content: String,
                        @Field("imgFile") imgFile: File?,
                        @Field("scriptUuid") scriptUuid: String): Single<ResponseEnvelope<ReplyResponse>>

    @GET("/apis/scripts/mine")
    fun fetchMyScripts(): Single<ResponseEnvelope<List<ScriptResponse>>>

    @GET("/apis/scraps")
    fun fetchScrapesScripts(): Single<ResponseEnvelope<List<ScriptResponse>>>

    @FormUrlEncoded
    @POST("/apis/scrap")
    fun fetchAddScrape(@Field("scriptUuid") scriptUuid: String): Single<ResponseEnvelope<ScriptResponse>>

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
