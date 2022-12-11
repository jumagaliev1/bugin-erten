package com.example.bugin_erten.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QaraSozDao {
    @Insert
    suspend fun insert(qaraSoz: QaraSoz)

    @Update
    suspend fun update(qaraSoz: QaraSoz)

    @Query("SELECT * from qarasozder_table WHERE sozId = :key")
    suspend fun get(key: Long): QaraSoz?

    @Query("SELECT * from qarasozder_table WHERE sozId = :key")
    fun getSozWithId(key: Long): LiveData<QaraSoz>

    @Query("DELETE FROM qarasozder_table")
    fun clear()

    @Query("DELETE FROM qarasozder_table WHERE sozId = :key")
    suspend fun delete(key: Long)

    @Query("SELECT * FROM qarasozder_table")
    fun getAll(): LiveData<List<QaraSoz?>>

    @Query("SELECT * FROM qarasozder_table WHERE favorite = 1")
    fun getAllFav(): LiveData<List<QaraSoz?>>
}