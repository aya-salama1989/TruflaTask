package com.trufla.task.core.modules

import android.app.Application
import androidx.room.Room
import com.trufla.task.app.data.locale.LibrariesRoomDB
import com.trufla.task.app.data.locale.PageDao
import dagger.Module
import dagger.Provides


@Module
class DataBaseModule {

    @Provides
    fun provideDb(app: Application): LibrariesRoomDB {
        return LibrariesRoomDB.getDatabase(app)
    }

    @Provides
    fun providePageDao(db: LibrariesRoomDB): PageDao {
        return db.pageDao()
    }
}