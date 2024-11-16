package com.itn.myapplicationaxen.data.datasources

import com.itn.myapplicationaxen.data.network.response.RandomJokeResponse
import com.itn.myapplicationaxen.core.utils.Resource

interface DataSource {

    suspend fun getJokeCategories(): Resource<List<String>>
    suspend fun getRandomJoke(category: String): Resource<RandomJokeResponse>
}