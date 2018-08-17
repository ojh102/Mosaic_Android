package com.teamnexters.mosaic.ui.common.theme

import android.os.Parcel
import android.os.Parcelable

internal data class ThemeData(
        val emoji: String,
        val theme: String,
        var selected: Boolean = false

) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(emoji)
        parcel.writeString(theme)
        parcel.writeByte(if (selected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ThemeData> {
        override fun createFromParcel(parcel: Parcel): ThemeData {
            return ThemeData(parcel)
        }

        override fun newArray(size: Int): Array<ThemeData?> {
            return arrayOfNulls(size)
        }
    }
}