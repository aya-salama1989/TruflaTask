package com.trufla.task.app.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PageDao {

    @Query("SELECT * FROM libraries_table WHERE `index` = :pageNumber ")
    suspend fun getPage(pageNumber: Int): Page

    @Query("SELECT EXISTS (SELECT 1 FROM libraries_table WHERE `index` = :pageNumber)")
    suspend fun pageExists(pageNumber: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(page: Page)

}