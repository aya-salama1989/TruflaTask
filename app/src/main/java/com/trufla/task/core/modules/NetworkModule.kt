package com.trufla.task.core.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import com.trufla.task.BuildConfig
import com.trufla.task.app.data.remote.GetLibrariesAPI
import com.trufla.task.core.BaseApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "https://api.github.com"

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOKHttp()).build()
    }


    @Singleton
    @Provides
    fun provideOKHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {// in debug mode show logs
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideGetLibrariesAPI(retrofit: Retrofit): GetLibrariesAPI {
        return retrofit.create(GetLibrariesAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideConnectivityManager(context: Application): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Singleton
    @Provides
    fun provideNetworkConnectivity(cm: ConnectivityManager): NetworkInfo {
        return cm.activeNetworkInfo!!
    }
}





