package com.example.laporkami

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var email:String,
    var nama:String,
//    var password:String,
    var noTelp:String) :Parcelable{

    override fun toString(): String {
        return super.toString()
    }
}