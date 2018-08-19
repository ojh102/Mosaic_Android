package com.teamnexters.mosaic.data.remote.model


internal data class WriterResponse(
        val uuid: String = "",
        val nick: String = "",
        val university: UniversityResponse,
        val username: String = "",
        val myScriptCnt: Int = 0,
        val myScrapCnt: Int = 0
)