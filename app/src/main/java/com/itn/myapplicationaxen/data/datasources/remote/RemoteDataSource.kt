package com.itn.myapplicationaxen.data.datasources.remote

import com.itn.myapplicationaxen.data.network.ApiService
import com.itn.myapplicationaxen.data.datasources.DataSource
import com.itn.myapplicationaxen.data.network.response.RandomJokeResponse
import com.itn.myapplicationaxen.core.utils.Resource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : DataSource {
    override suspend fun getJokeCategories(): Resource<List<String>> {
        return try {
            val response = apiService.getJokeCategories()
            if (!response.isSuccessful) {
                Resource.Error.ServerError()
            } else {
                val body = response.body() ?: return Resource.Error.ServerError()
                Resource.Success(body)
            }
        } catch (e: Exception) {
            Resource.Error.UnknownError()
        } catch (e: retrofit2.HttpException) {
            Resource.Error.ServerError()
        }
    }

    override suspend fun getRandomJoke(category: String): Resource<RandomJokeResponse> {
        return try {
            val response = apiService.getRandomJoke(category)
            if (!response.isSuccessful) {
                Resource.Error.ServerError()
            } else {
                val body = response.body() ?: return Resource.Error.ServerError()
                Resource.Success(body)
            }
        } catch (e: Exception) {
            Resource.Error.UnknownError()
        } catch (e: retrofit2.HttpException) {
            Resource.Error.ServerError()
        }
    }
}