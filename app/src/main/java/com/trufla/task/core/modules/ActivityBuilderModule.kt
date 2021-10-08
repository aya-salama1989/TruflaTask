package com.trufla.task.core.modules


import androidx.lifecycle.ViewModel
import com.trufla.task.app.data.GetLibrariesRepoImpl
import com.trufla.task.app.data.locale.PageDao
import com.trufla.task.app.data.remote.RemoteDS
import com.trufla.task.app.domain.GetLibrariesRepo
import com.trufla.task.app.presentation.MainActivity
import com.trufla.task.app.presentation.MainFragment
import com.trufla.task.app.presentation.MainViewModel
import com.trufla.task.core.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [MainFragmentModule::class]
    )
    abstract fun injectMainActivity(): MainActivity

}

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            MainViewModelModule::class,
            GetLibrariesRepoModule::class
        ]
    )
    abstract fun bindMainFragment(): MainFragment

}


@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindLibrariesViewModel(mainViewModel: MainViewModel): ViewModel
}

@Module
class GetLibrariesRepoModule {

    @Provides
    fun provideLibrariesRepo(remoteDS: RemoteDS, pageDao: PageDao): GetLibrariesRepo =
        GetLibrariesRepoImpl(remoteDS, pageDao)
}