package com.teamnexters.mosaic.ui.filter

import android.os.Parcel
import android.os.Parcelable

internal data class FilterData(
        val emoji: String,
        val theme: String,
        var selected: Boolean = false

) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(emoji)
        parcel.writeString(theme)
        parcel.writeByte(if (selected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterData> {
        override fun createFromParcel(parcel: Parcel): FilterData {
            return FilterData(parcel)
        }

        override fun newArray(size: Int): Array<FilterData?> {
            return arrayOfNulls(size)
        }
    }
}