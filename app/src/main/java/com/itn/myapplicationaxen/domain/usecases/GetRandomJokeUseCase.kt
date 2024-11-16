package com.itn.myapplicationaxen.domain.usecases

import com.itn.myapplicationaxen.data.db.repositories.RandomJokeRepository
import com.itn.myapplicationaxen.core.utils.Resource
import com.itn.myapplicationaxen.data.network.response.RandomJokeResponse
import javax.inject.Inject

class GetRandomJokeUseCase @Inject constructor(private val repository: RandomJokeRepository) {
    suspend operator fun invoke(category: String): Resource<RandomJokeResponse> {
        return repository.getRandomJoke(category)
    }
}