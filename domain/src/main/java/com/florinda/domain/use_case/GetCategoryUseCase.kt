package com.florinda.domain.use_case

import com.florinda.domain.Resource
import com.florinda.domain.model.Categories
import com.florinda.domain.model.Products
import com.florinda.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCategoryUseCase constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Resource<Categories>> = flow {
        emit(Resource.Loading)
        try {
            val categories = repository.getCategories()
            emit(Resource.Success(categories))
        }catch (exception : Throwable){
            emit(Resource.Error(exception))
        }
    }.flowOn(Dispatchers.IO)
}