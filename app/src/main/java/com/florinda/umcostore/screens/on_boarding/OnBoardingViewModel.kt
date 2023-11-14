package com.florinda.umcostore.screens.on_boarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnBoardingViewModel  constructor(): ViewModel() {


    private var _fabNextOnBoardingScreen  =  MutableLiveData<Boolean>()
    val fabNextOnBoardingScreen : LiveData<Boolean>
    get() = _fabNextOnBoardingScreen


    private var _backBoard  =  MutableLiveData<Boolean>()
    val backBoard : LiveData<Boolean>
    get() = _backBoard

   private var _skipToNextOnBoarding  =  MutableLiveData<Boolean>()
    val skipToNextOnBoarding : LiveData<Boolean>
    get() = _skipToNextOnBoarding



    init {
        _backBoard.value = false
        _skipToNextOnBoarding.value = false
        _fabNextOnBoardingScreen.value = false
    }




    fun fabNextOnBoardingScreenClicked(){ _fabNextOnBoardingScreen.value = true }
    fun backBoardClicked(){ _backBoard.value = true }
    fun skipToNextOnBoardingClicked(){ _skipToNextOnBoarding.value = true }


    fun fabNextOnBoardingScreenClickedComplete(){
        _fabNextOnBoardingScreen.value = false
    }

    fun backBoardClickedComplete(){
        _backBoard.value = false
    }

    fun skipToNextOnBoardingClickedComplete(){
        _skipToNextOnBoarding.value = false
    }



}