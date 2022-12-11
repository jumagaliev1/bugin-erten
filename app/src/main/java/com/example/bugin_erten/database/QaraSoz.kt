package com.example.bugin_erten.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qarasozder_table")
data class QaraSoz(
    @PrimaryKey(autoGenerate = true)
    var sozId: Long = 0L,

    @ColumnInfo(name = "title")
    var qaraSozTitle: String = "",

    @ColumnInfo(name = "text")
    var qaraSozText: String = "",

    @ColumnInfo(name = "favorite")
    var qaraSozFav: Int = 0,
    )
