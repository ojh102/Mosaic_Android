package com.teamnexters.mosaic.data.remote.model

internal data class ReplyResponse(
        val idx : Int,
        val uuid: String,
        val upperReplyUuid: String,
        val createdAt : Long,
        val content: String,
        val depth : Int,
        val imgUrl: String = "",
        val scriptUuid: String,
        val thumbnailUrl: String,
        val updatedAt: Long,
        val childReplies: List<ChildReplyResponse>,
        val writer : WriterResponse)