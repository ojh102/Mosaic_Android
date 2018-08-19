package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface MosaicApi {
    @POST("/login/email")
    fun sendEmail(
            @Field("email") email: String
    ): Call<EmailSendResponse>

    @GET("/apis/scripts")
    fun fetchScripts(): Single<ResponseEnvelope<List<ScriptResponse>>>

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
}
