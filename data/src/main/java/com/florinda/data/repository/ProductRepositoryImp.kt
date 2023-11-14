package com.florinda.data.repository

import com.florinda.data.remote.ApiService
import com.florinda.domain.model.Products
import com.florinda.domain.repository.ProductsRepository

class ProductRepositoryImp constructor(
    private val apiService: ApiService
) : ProductsRepository {
    override suspend fun getProductsFromRemote(): Products  = apiService.getProductsAsync().await().body()!!
}
