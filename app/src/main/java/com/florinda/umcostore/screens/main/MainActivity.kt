package com.florinda.umcostore.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.florinda.umcostore.R
import com.florinda.umcostore.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavWithNavController()
        handleNavigation()
        setupObservableData()




    }



    private fun setupBottomNavWithNavController(){
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun handleNavigation(){
        navController.addOnDestinationChangedListener { _, _, _ ->
            if (navController.currentDestination?.id in listOf(
                    R.id.loginFragment
            )){
                binding.bottomNavigationView.visibility = View.GONE
            }
        }
    }

    private fun setupObservableData(){

    }


}