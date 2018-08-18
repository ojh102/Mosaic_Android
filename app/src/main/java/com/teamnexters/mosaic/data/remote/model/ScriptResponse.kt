package com.teamnexters.mosaic.data.remote.model


internal data class ScriptResponse(
        val idx: Int = 0,
        val uuid: String = "",
        val content: String = "",
        val writer: WriterResponse,
        val imgUrls: List<String> = listOf(),
        val thumbnailUrls: List<String> = listOf(),
        val created: Long = 0L,
        val category: CategoryResponse,
        val replies: Int,
        val scrap: Boolean = false
) {
    fun getDate(): String {
        return "$created"
    }
}

