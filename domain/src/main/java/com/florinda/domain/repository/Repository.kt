package com.florinda.domain.repository

import com.florinda.domain.model.Categories
import com.florinda.domain.model.Products

interface Repository {
    suspend fun getProducts() : Products
    suspend fun getCategories() : Categories
}