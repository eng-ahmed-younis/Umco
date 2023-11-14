package com.florinda.umcostore.screens.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.florinda.umcostore.R
import com.florinda.umcostore.screens.main.MainActivity
import com.florinda.umcostore.screens.on_boarding.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moveToNextScreen()
    }

    private fun moveToNextScreen(){
        startActivity(Intent(this, OnBoardingActivity::class.java))
        finish()
    }
}