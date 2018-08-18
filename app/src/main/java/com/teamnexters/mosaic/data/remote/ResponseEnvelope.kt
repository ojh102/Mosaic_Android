package com.teamnexters.mosaic.data.remote

data class ResponseEnvelope<T>(
        val message: String? = null,
        var responseCode: Int = 0,
        var result: T? = null
)