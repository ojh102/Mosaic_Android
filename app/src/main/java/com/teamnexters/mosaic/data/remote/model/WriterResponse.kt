package com.teamnexters.mosaic.data.remote.model

import android.os.Parcel
import android.os.Parcelable


internal data class WriterResponse(
        val uuid: String = "",
        val nick: String = "",
        val university: UniversityResponse,
        val username: String? = "",
        val email: String? = "",
        val myScriptCnt: Int = 0,
        val myScrapCnt: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(UniversityResponse::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uuid)
        parcel.writeString(nick)
        parcel.writeParcelable(university,flags)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeInt(myScriptCnt)
        parcel.writeInt(myScrapCnt)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<WriterResponse> {
        override fun createFromParcel(parcel: Parcel): WriterResponse {
            return WriterResponse(parcel)
        }

        override fun newArray(size: Int): Array<WriterResponse?> {
            return arrayOfNulls(size)
        }
    }
}