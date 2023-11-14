package com.florinda.data.repository

import com.florinda.data.remote.ApiService
import com.florinda.domain.model.Categories
import com.florinda.domain.repository.CategoryRepository

class CategoriesRepositoryImpl constructor(
    private val apiService: ApiService
): CategoryRepository {
    override suspend fun getProductCategory(): Categories  = apiService.getCategoriesAsync()
}