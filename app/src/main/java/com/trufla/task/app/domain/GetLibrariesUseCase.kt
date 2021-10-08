package com.trufla.task.app.domain

import com.trufla.task.app.data.toEntity
import javax.inject.Inject

class GetLibrariesUseCase @Inject constructor(private val getLibrariesRepo: GetLibrariesRepo) {

    suspend fun getLibraries(): List<LibraryEntity>? {
        return getLibrariesRepo.getJacksRepos()
    }
}