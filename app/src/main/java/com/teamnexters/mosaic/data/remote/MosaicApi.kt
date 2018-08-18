package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.EmailSendResponse
import com.teamnexters.mosaic.data.remote.model.ResponseEnvelope
import com.teamnexters.mosaic.data.remote.model.ScriptResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

internal interface MosaicApi {
    @POST("/login/email")
    fun sendEmail(
            @Field("email") email: String
    ): Call<EmailSendResponse>

    @GET("/apis/scripts")
    fun fetchCards(): Single<ResponseEnvelope<List<ScriptResponse>>>
}