package com.example.sahibj.moviebuffs.dagger

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.sahibj.moviebuffs.interceptors.MovieServiceInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton


/**
 * Created by sahibj on 9/25/17.
 */
@Module
class NetworkModule(val baseUrl: String) {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(application.cacheDir, cacheSize.toLong())
        return cache
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .cache(cache).build()
        return client
    }

    @Provides
    @Singleton
    fun provideRetrofit(application: Application, okHttpClient: OkHttpClient): Retrofit {

        val okHttpClient = okHttpClient.newBuilder()
                .addInterceptor(MovieServiceInterceptor(application))
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit
    }
}