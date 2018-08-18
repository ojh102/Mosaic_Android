package com.teamnexters.mosaic.data.remote

import com.teamnexters.mosaic.data.remote.model.EmailSendResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface MosaicApi {
    @POST("/login/email")
    fun sendEmail(
            @Field("email") email: String
    ): Call<EmailSendResponse>
}