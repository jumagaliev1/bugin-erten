package com.example.bugin_erten.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QaraSozDao {
    @Insert
    fun insert(qaraSoz: QaraSoz)

    @Update
    fun update(qaraSoz: QaraSoz)

    @Query("SELECT * from qarasozder_table WHERE sozId = :key")
    fun get(key: Long): QaraSoz?

    @Query("DELETE FROM qarasozder_table")
    fun clear()

    @Query("SELECT * FROM qarasozder_table ORDER BY sozId DESC")
    fun getAll(): LiveData<List<QaraSoz>>
}