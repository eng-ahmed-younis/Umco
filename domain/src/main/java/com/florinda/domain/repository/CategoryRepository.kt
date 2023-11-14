package com.florinda.domain.repository

import com.florinda.domain.model.Categories

interface CategoryRepository {
    suspend fun getProductCategory() : Categories
}