package com.trufla.task.app.data.locale

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Page::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class LibrariesRoomDB : RoomDatabase() {

    abstract fun pageDao(): PageDao

    companion object {

        @Volatile
        private var INSTANCE: LibrariesRoomDB? = null

        fun getDatabase(context: Context): LibrariesRoomDB {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibrariesRoomDB::class.java,
                    "libraries_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}