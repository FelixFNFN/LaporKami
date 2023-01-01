package com.example.laporkami

import androidx.room.*

@Dao
interface UserDAO {
    @Insert
    suspend fun insert(user: Users)

    @Update
    suspend fun update(user: Users)

    @Delete
    suspend fun delete(user: Users)

    @Query("DELETE FROM users where id = :id")
    suspend fun deleteQuery(id:Long)

    @Query("SELECT * FROM users")
    suspend fun fetch():List<Users>

    @Query("SELECT * FROM users where nama = :nama")
    suspend fun getUsername(nama: String):Users?

    @Query("SELECT * FROM users where id = :id")
    suspend fun getid(id: Long):Users?
}