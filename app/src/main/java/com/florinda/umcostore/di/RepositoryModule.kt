package com.florinda.umcostore.di

import com.florinda.data.remote.ApiService
import com.florinda.data.repository.CategoriesRepositoryImpl
import com.florinda.data.repository.ProductRepositoryImp
import com.florinda.data.repository.RepositoryImpl
import com.florinda.domain.repository.CategoryRepository
import com.florinda.domain.repository.ProductsRepository
import com.florinda.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideProductRepository(
        apiService: ApiService
    ): ProductsRepository{
        return ProductRepositoryImp(apiService =  apiService)
    }

    @Provides
    fun provideCategoryRepository(
        apiService: ApiService
    ): CategoryRepository{
        return CategoriesRepositoryImpl(apiService =  apiService)
    }


    @Provides
    fun provideMainRepository(
        productsRepository: ProductsRepository,
        categoryRepository: CategoryRepository
    ) : Repository{
        return RepositoryImpl(
            productsRepository = productsRepository,
            categoryRepository = categoryRepository
        )
    }

}


