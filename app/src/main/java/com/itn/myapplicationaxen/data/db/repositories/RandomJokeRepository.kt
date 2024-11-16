package com.itn.myapplicationaxen.data.db.repositories

import com.itn.myapplicationaxen.core.utils.Resource
import com.itn.myapplicationaxen.data.datasources.remote.RemoteDataSource
import com.itn.myapplicationaxen.data.network.response.RandomJokeResponse
import javax.inject.Inject

interface RandomJokeRepository {
    suspend fun getRandomJoke(category: String): Resource<RandomJokeResponse>
}

class RandomJokeRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    RandomJokeRepository {
    override suspend fun getRandomJoke(category: String): Resource<RandomJokeResponse> {
        return try {
            val result = remoteDataSource.getRandomJoke(category)
            if (result !is Resource.Success<*>) {
                Resource.Error.ServerError(null)
            }
            result
        } catch (e:Exception) {
            Resource.Error.UnknownError()
        }
    }
}