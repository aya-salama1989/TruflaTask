package com.trufla.task.core.component

import android.app.Application
import android.os.Parcelable
import com.trufla.task.core.BaseApplication

import com.trufla.task.core.modules.ActivityBuilderModule
import com.trufla.task.core.modules.DataBaseModule
import com.trufla.task.core.modules.NetworkModule
import com.trufla.task.core.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DataBaseModule::class,
        NetworkModule::class,
        ActivityBuilderModule::class,
        ViewModelFactoryModule::class
    ]
)
@Singleton
interface ApplicationComponent: AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

}