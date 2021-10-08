package com.trufla.task.app.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface GetLibrariesAPI {

    @GET("users/JakeWharton/repos?")
    suspend fun getRepos(
        @Query("page") page_number: Int,
        @Query("per_page") per_page: String = "15",
    ): List<LibrariesResponseItem>
}