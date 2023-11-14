package com.florinda.umcostore.di

import com.florinda.domain.repository.Repository
import com.florinda.domain.use_case.GetCategoryUseCase
import com.florinda.domain.use_case.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Provides
    fun provideGetProductsUseCase(
        repository: Repository
    ): GetProductsUseCase{
        return GetProductsUseCase(repository)
    }

    @Provides
    fun provideCategoryUeCase(
        repository: Repository
    ): GetCategoryUseCase{
        return GetCategoryUseCase(repository)
    }

}