package com.trufla.task.app.data

import com.trufla.task.app.data.remote.LibrariesResponseItem
import com.trufla.task.app.domain.LibraryEntity


fun LibrariesResponseItem.toEntity() = LibraryEntity(
    libraryName = name,
    watchersCount = watchers.toString()
)