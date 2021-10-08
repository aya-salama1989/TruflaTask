package com.trufla.task.core.modules

import androidx.lifecycle.ViewModelProvider
import com.trufla.task.core.BaseViewModelProvider
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(baseViewModelFactory: BaseViewModelProvider):
            ViewModelProvider.Factory
}





