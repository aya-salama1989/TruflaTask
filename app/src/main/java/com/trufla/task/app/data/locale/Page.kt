package com.trufla.task.app.data.locale

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.trufla.task.app.domain.LibraryEntity

@Entity(tableName = "libraries_table")
@TypeConverters(DataConverter::class)
data class Page(@PrimaryKey @ColumnInfo(name = "index") val id: Int,
                @ColumnInfo(name = "page") val librariesPage: List<LibraryEntity>)
