package com.trufla.task.app.data.locale

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trufla.task.app.domain.LibraryEntity
import java.lang.reflect.Type


object DataConverter {

    @TypeConverter
    @JvmStatic
    fun fromLibraryList(page: List<LibraryEntity>): String? {
        val type: Type = object : TypeToken<List<LibraryEntity>>() {}.type

        return Gson().toJson(page, type)
    }

    @TypeConverter
    @JvmStatic
    fun toLibraryList(pageString: String?): List<LibraryEntity>? {
        if (pageString == null) {
            return null
        }
        val type: Type = object : TypeToken<List<LibraryEntity>>() {}.type
        return Gson().fromJson<List<LibraryEntity>>(pageString, type)
    }
}