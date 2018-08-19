package com.teamnexters.mosaic.data.remote.model

import android.os.Parcel
import android.os.Parcelable


internal data class CategoryResponse(
        val emoji: String = "",
        val name: String = "",
        var selected: Boolean = false

) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()
    )

    fun getTheme(): String {
        return name + emoji
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(emoji)
        parcel.writeByte(if (selected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryResponse> {
        override fun createFromParcel(parcel: Parcel): CategoryResponse {
            return CategoryResponse(parcel)
        }

        override fun newArray(size: Int): Array<CategoryResponse?> {
            return arrayOfNulls(size)
        }
    }
}