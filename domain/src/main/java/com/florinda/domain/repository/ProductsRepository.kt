package com.florinda.domain.repository

import com.florinda.domain.model.Products

interface ProductsRepository {
    suspend fun getProductsFromRemote() : Products
}
