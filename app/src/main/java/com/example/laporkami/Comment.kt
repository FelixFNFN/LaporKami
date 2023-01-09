package com.example.laporkami

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    var id:Long,
    var id_laporan:Long,
    var id_user:Long,
    var comment:String
) : Parcelable {
}