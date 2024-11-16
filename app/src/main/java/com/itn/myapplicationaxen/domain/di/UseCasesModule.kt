package com.itn.myapplicationaxen.domain.di
import com.itn.myapplicationaxen.data.db.repositories.CategoriesRepository
import com.itn.myapplicationaxen.data.db.repositories.CategoriesRepositoryImpl
import com.itn.myapplicationaxen.data.db.repositories.RandomJokeRepository
import com.itn.myapplicationaxen.data.db.repositories.RandomJokeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    @Binds
    fun provideCategoriesRepository(categoriesRepository: CategoriesRepositoryImpl) : CategoriesRepository

    @Binds
    fun provideRandomJokeRepository(randomJokeRepository: RandomJokeRepositoryImpl) : RandomJokeRepository


}