package com.example.bugin_erten.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QaraSoz::class], version = 4, exportSchema = false)
abstract class QaraSozDatabase:RoomDatabase() {
    abstract val qaraSozDao: QaraSozDao

    companion object {

        @Volatile
        private var INSTANCE: QaraSozDatabase? = null

        fun getInstance(context: Context): QaraSozDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QaraSozDatabase::class.java,
                        "qarasozder_database"
                    )
                        .createFromAsset("database/qarasozder_database.sqlite")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}