package com.example.laporkami

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "laporan")
data class Laporan(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var laporan:String
) {
}