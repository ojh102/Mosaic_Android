package com.teamnexters.mosaic.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class EmailSendResponse(
        @SerializedName("httpStatus") @Expose var httpStatus: Int,
        @SerializedName("message") @Expose var message: String,
        @SerializedName("responseCode") @Expose var responseCode: Int,
        @SerializedName("result") @Expose var result: String)