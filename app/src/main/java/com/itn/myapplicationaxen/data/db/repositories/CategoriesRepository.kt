package com.itn.myapplicationaxen.data.db.repositories

import com.itn.myapplicationaxen.core.utils.Resource
import com.itn.myapplicationaxen.data.datasources.remote.RemoteDataSource
import javax.inject.Inject

interface CategoriesRepository {
    suspend fun getJokeCategories(): Resource<List<String>>
}

class CategoriesRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    CategoriesRepository {
    override suspend fun getJokeCategories(): Resource<List<String>> {
        return try {
            val result = remoteDataSource.getJokeCategories()
            if (result !is Resource.Success<*>) {
                Resource.Error.ServerError(null)
            }
            result
        } catch (e:Exception) {
            Resource.Error.UnknownError()
        }
    }
}