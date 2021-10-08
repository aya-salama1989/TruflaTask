package com.trufla.task.app.data.remote

import javax.inject.Inject

class RemoteDS @Inject constructor(private val getLibrariesAPI: GetLibrariesAPI) {

    suspend fun getLibraries(pagerNumber:Int):List<LibrariesResponseItem>{
        return getLibrariesAPI.getRepos(pagerNumber)
    }
}