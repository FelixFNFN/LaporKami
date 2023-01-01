package com.example.laporkami

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Pertanyaan(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var pertanyaan:String
){
}