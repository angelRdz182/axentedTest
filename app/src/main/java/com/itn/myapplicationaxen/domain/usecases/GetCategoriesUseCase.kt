package com.itn.myapplicationaxen.domain.usecases

import com.itn.myapplicationaxen.data.db.repositories.CategoriesRepository
import com.itn.myapplicationaxen.core.utils.Resource
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: CategoriesRepository) {
    suspend operator fun invoke(): Resource<List<String>> {
        return repository.getJokeCategories()
    }
}