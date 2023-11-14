package com.florinda.domain.use_case

import android.util.Log
import com.florinda.domain.Resource
import com.florinda.domain.model.Products
import com.florinda.domain.repository.ProductsRepository
import com.florinda.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetProductsUseCase constructor(
    private val repository: Repository
){
    operator fun invoke(): Flow<Resource<Products>> = flow {
        emit(Resource.Loading)
        Log.i("displayList_tag","GetProductsUseCase")

        try { 
            val products: Products = repository.getProducts()
            emit(Resource.Success(products))
        }catch (exception : Throwable){
            emit(Resource.Error(exception))
        }
    }.flowOn(Dispatchers.IO)

}