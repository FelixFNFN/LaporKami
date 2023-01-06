package com.example.laporkami

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "aktifitas")
@Parcelize
data class Aktifitas(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var nik:String,
    var nama:String,
    var aktivitas:String,
    var statusCode:Int
):Parcelable {

}