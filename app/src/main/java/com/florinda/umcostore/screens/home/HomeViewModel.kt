package com.florinda.umcostore.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.domain.Resource
import com.florinda.domain.use_case.GetCategoryUseCase
import com.florinda.domain.use_case.GetProductsUseCase
import com.florinda.umcostore.di.Dispatcher
import com.florinda.umcostore.di.DispatcherAnnotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Dispatcher(DispatcherAnnotation.Io) private val ioDispatcher: CoroutineDispatcher,
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoryUseCase: GetCategoryUseCase
) : ViewModel(){

    val intentChannel = Channel<HomeIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<HomeStates>(HomeStates.Idle)
    val state: StateFlow<HomeStates>
        get() = _state


    init {
        onEvent()
    }



    // act as process
    private fun onEvent() {
        viewModelScope.launch (){
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    is HomeIntent.GetProducts -> {

                        getProducts()
                    }
                    is HomeIntent.GetCategories -> getCategories()
                }
            }
        }
    }



    // act as produce
    private fun getProducts(){

        viewModelScope.launch (){
            getProductsUseCase().collect{
                _state.value = when(it){
                    is Resource.Loading ->{ HomeStates.Loading }
                    is Resource.Success-> { HomeStates.GetProducts(products = it.data) }
                    is Resource.Error->{ HomeStates.Error(message = it.exception) }
                }
            }
        }
    }



    private fun getCategories(){
        viewModelScope.launch {
            getCategoryUseCase().collect{
                _state.value = when(it){
                    is Resource.Loading ->{ HomeStates.Loading }
                    is Resource.Success-> { HomeStates.GetCategories(categories = it.data)}
                    is Resource.Error->{ HomeStates.Error(message = it.exception) }
                }
            }
        }
    }

}