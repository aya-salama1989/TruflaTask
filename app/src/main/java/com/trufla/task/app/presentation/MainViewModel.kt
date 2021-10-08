package com.trufla.task.app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trufla.task.app.domain.GetLibrariesUseCase
import com.trufla.task.app.domain.LibraryEntity
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val getLibrariesUseCase: GetLibrariesUseCase) :
    ViewModel() {

    private var _libraries = MutableLiveData<ArrayList<LibraryEntity>>()

    val libraries: LiveData<ArrayList<LibraryEntity>> get() = _libraries

    fun getLibrariesList() {
        viewModelScope.launch {
            val libs = getLibrariesUseCase.getLibraries() as ArrayList
            _libraries.value = libs
        }
    }
}