package com.teamnexters.mosaic.data.remote.model


internal data class WriterResponse(
        val uuid: String = "",
        val nick: String = "",
        val university: UniversityResponse,
        val username: String = ""

)