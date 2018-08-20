package com.teamnexters.mosaic.data.remote.model

import android.os.Parcel
import android.os.Parcelable


internal data class UniversityResponse(
        val idx: Int = 0,
        val name: String = "",
        val domain: String = "",
        val imgUrl: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idx)
        parcel.writeString(name)
        parcel.writeString(domain)
        parcel.writeString(imgUrl)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<UniversityResponse> {
        override fun createFromParcel(parcel: Parcel): UniversityResponse {
            return UniversityResponse(parcel)
        }

        override fun newArray(size: Int): Array<UniversityResponse?> {
            return arrayOfNulls(size)
        }
    }
}