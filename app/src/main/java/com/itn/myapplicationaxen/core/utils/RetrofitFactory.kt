package com.itn.myapplicationaxen.core.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    fun createRetrofitInstance(baseUrl: String): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}