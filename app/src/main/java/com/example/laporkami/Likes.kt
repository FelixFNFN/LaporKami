package com.example.laporkami

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Likes(
    var id:Long,
    var id_laporan:Long,
    var id_user:Long
    ) : Parcelable {
}