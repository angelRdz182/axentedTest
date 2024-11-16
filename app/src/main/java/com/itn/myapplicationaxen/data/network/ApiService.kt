package com.itn.myapplicationaxen.data.network

import com.itn.myapplicationaxen.data.network.response.RandomJokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/jokes/categories")
    suspend fun getJokeCategories(): Response<List<String>>

    @GET("/jokes/random")
    suspend fun  getRandomJoke(@Query("category") category: String): Response<RandomJokeResponse>


}