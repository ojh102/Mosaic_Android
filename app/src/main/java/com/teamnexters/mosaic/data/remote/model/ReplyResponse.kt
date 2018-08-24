package com.teamnexters.mosaic.data.remote.model

import java.text.SimpleDateFormat
import java.util.*

internal data class ReplyResponse(
        val idx : Int = -0,
        val uuid: String = "",
        val upperReplyUuid: String = "",
        val upperReplyNick : String = "",
        val createdAt : Long = 0,
        val content: String = "",
        val depth : Int = 0,
        val imgUrl: String = "",
        val scriptUuid: String = "",
        val thumbnailUrl: String = "",
        val updatedAt: Long = 0,
        val childReplies: List<ChildReplyResponse> = listOf(),
        val writer : WriterResponse){

    fun getDate(): String {
        val curCal = Calendar.getInstance(Locale.KOREAN)
        curCal.timeInMillis = System.currentTimeMillis()

        val curYear = curCal[Calendar.YEAR]
        val curMonth = curCal[Calendar.MONTH] + 1
        val curDay = curCal[Calendar.DAY_OF_MONTH]

        val cal = Calendar.getInstance(Locale.KOREAN)
        cal.timeInMillis = createdAt

        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH] + 1
        val day = cal[Calendar.DAY_OF_MONTH]

        return when {
            (year < curYear) -> {
                SimpleDateFormat("yy.MM.dd", Locale.KOREAN).format(createdAt)
            }
            (year == curYear && month == curMonth && day == curDay) -> {
                SimpleDateFormat("aa hh:mm", Locale.KOREAN).format(createdAt)
            }
            else -> {
                SimpleDateFormat("MM.dd", Locale.KOREAN).format(createdAt)
            }
        }
    }
}