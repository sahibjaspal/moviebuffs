package com.example.sahibj.moviebuffs.interceptors

import android.app.Application
import com.example.sahibj.moviebuffs.R
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by sahibj on 9/30/17.
 */
class MovieServiceInterceptor(application: Application) : Interceptor {

    private val apiKey: String = application.getString(R.string.api_key)

    override fun intercept(chain: Interceptor.Chain): Response {

        val urlBuilder = chain.request().url().newBuilder()
        urlBuilder.addQueryParameter("api_key", apiKey)

        val request = chain.request().newBuilder().url(urlBuilder.build()).build()
        return chain.proceed(request)
    }
}