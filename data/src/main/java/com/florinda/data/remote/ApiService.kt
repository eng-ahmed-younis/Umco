package com.florinda.data.remote

import com.florinda.domain.model.Categories
import com.florinda.domain.model.Products
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProductsAsync(): Deferred<Response<Products>>

    @GET("categories")
    fun getCategoriesAsync() : Categories
}