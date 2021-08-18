package com.example.golftracker

import android.os.Parcel
import android.os.Parcelable

data class Hole(val hole: String, var numberOfShots: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hole)
        parcel.writeInt(numberOfShots)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hole> {
        override fun createFromParcel(parcel: Parcel): Hole {
            return Hole(parcel)
        }

        override fun newArray(size: Int): Array<Hole?> {
            return arrayOfNulls(size)
        }
    }
}