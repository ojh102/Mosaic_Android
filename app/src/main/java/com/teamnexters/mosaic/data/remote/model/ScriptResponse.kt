package com.teamnexters.mosaic.data.remote.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter.writeByte



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
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idx)
        parcel.writeString(uuid)
        parcel.writeString(content)
        parcel.writeParcelable(writer,flags)
        parcel.writeList(imgUrls)
        parcel.writeList(thumbnailUrls)
        parcel.writeLong(created)
        parcel.writeParcelable(category,flags)
        parcel.writeInt(replies)
        parcel.writeByte(if (scrap) 1 else 0)
    }

    override fun describeContents(): Int = 0

    fun getDate(): String {
        return "$created"
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

