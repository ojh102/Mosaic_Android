package com.teamnexters.mosaic.ui.detail.data

import java.io.Serializable

data class DetailIntentData(
        val uuid : String,
        val contentId : String,
        val userId : String,
        val universityName : String,
        val isScraped : Boolean,
        val contentTitle : String,
        val contentImageList : ArrayList<String>,
        val content : String,
        val writeTime : String,
        val replyCount : Int
) : Serializable