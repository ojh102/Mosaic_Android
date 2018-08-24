package com.teamnexters.mosaic.data.remote.model

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*


internal data class ScriptResponse(
        val idx: Int = 0,
        val uuid: String = "",
        val content: String = "",
        val writer: WriterResponse,
        val imgUrls: List<String> = listOf(),
        val thumbnailUrls: List<String> = listOf(),
        val createdAt: Long = 0L,
        val category: CategoryResponse,
        var replies: Int,
        var scrap: Boolean = false
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(WriterResponse::class.java.classLoader),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.readLong(),
            parcel.readParcelable(CategoryResponse::class.java.classLoader),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()
    )

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idx)
        parcel.writeString(uuid)
        parcel.writeString(content)
        parcel.writeParcelable(writer, flags)
        parcel.writeStringList(imgUrls)
        parcel.writeStringList(thumbnailUrls)
        parcel.writeLong(createdAt)
        parcel.writeParcelable(category, flags)
        parcel.writeInt(replies)
        parcel.writeByte(if(scrap) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScriptResponse> {
        override fun createFromParcel(parcel: Parcel): ScriptResponse {
            return ScriptResponse(parcel)
        }

        override fun newArray(size: Int): Array<ScriptResponse?> {
            return arrayOfNulls(size)
        }
    }

}

