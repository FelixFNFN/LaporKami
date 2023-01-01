package com.example.laporkami

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aktifitas")
data class Aktifitas(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var nik:String,
    var nama:String,
    var aktivitas:String,
    var statusCode:Int
) {

}