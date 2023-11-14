package com.florinda.umcostore.screens.home

import com.florinda.domain.model.Categories
import com.florinda.domain.model.Products

sealed class HomeStates {
    data object Idle : HomeStates()
    data object Loading : HomeStates()
  //  data class Success(val orders  : Products) : HomeStateView()
    data class Error(val message : Throwable) : HomeStates()
    data class GetProducts(val products: Products) : HomeStates()
    data class GetCategories(val categories: Categories) : HomeStates()

}