package com.teamnexters.mosaic.ui.detail.data

import java.util.*

data class RereplyDetailData(
        val uuid: Long,
        val replyId: String,
        val universityImageUrl: String,
        val universityName: String,
        val userId: String,
        val isMy: Boolean,
        val replyContent: String,
        val writeTime: Date,
        val imageUrl: String)