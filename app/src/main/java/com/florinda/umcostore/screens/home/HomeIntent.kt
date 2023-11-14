package com.florinda.umcostore.screens.home

sealed class HomeIntent {
    data object GetProducts  : HomeIntent()
    data object GetCategories : HomeIntent()
}