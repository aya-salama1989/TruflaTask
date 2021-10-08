package com.trufla.task.app.domain

import com.trufla.task.app.data.remote.LibrariesResponseItem

interface GetLibrariesRepo {

    suspend fun getJacksRepos():List<LibraryEntity>?
}