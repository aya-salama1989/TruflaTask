package com.trufla.task.app.data

import androidx.annotation.WorkerThread
import com.trufla.task.app.data.locale.Page
import com.trufla.task.app.data.locale.PageDao
import com.trufla.task.app.data.remote.RemoteDS
import com.trufla.task.app.domain.GetLibrariesRepo
import com.trufla.task.app.domain.LibraryEntity
import javax.inject.Inject

class GetLibrariesRepoImpl
@Inject constructor(
    private val remoteDS: RemoteDS,
    private val pageDao: PageDao
) : GetLibrariesRepo {

    private var pageNumber:Int = 0

    override suspend fun getJacksRepos(): List<LibraryEntity>? {
        this.pageNumber += 1
        return if (pageDao.pageExists(pageNumber)) {
            getDataFromLocal(pageNumber)
        } else {
            getDataFromRemote(pageNumber)
        }
    }

    private suspend fun getDataFromRemote(pageNumber: Int): List<LibraryEntity>? {
        val libs = remoteDS.getLibraries(pageNumber).map { it.toEntity() }
        insert(Page(pageNumber, libs)) // needs to be don in a separate thread and notify the UI faster
        return libs
    }


    private suspend fun getDataFromLocal(pageNumber: Int): List<LibraryEntity>? {
        return pageDao?.getPage(pageNumber)?.librariesPage
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    private suspend fun insert(page: Page) {
        pageDao.insert(page)
    }
}
