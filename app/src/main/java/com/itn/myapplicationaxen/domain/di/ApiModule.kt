package com.itn.myapplicationaxen.domain.di

import android.app.Application
import android.content.Context
import com.itn.myapplicationaxen.core.Constants
import com.itn.myapplicationaxen.core.utils.RetrofitFactory
import com.itn.myapplicationaxen.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitFactory.createRetrofitInstance(Constants.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

}