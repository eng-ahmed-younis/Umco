package com.florinda.data.repository

import com.florinda.domain.model.Categories
import com.florinda.domain.model.Products
import com.florinda.domain.repository.CategoryRepository
import com.florinda.domain.repository.ProductsRepository
import com.florinda.domain.repository.Repository

class RepositoryImpl constructor(
    private val productsRepository: ProductsRepository,
    private val categoryRepository: CategoryRepository
) : Repository{

    override suspend fun getProducts(): Products  = productsRepository.getProductsFromRemote()

    override suspend fun getCategories(): Categories  = categoryRepository.getProductCategory()


}