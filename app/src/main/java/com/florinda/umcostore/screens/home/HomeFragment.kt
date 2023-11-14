package com.florinda.umcostore.screens.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.florinda.umcostore.databinding.FragmentHomeBinding
import com.florinda.umcostore.utils.BaseFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        renderState()


    }



    override fun setupViews() {
        getProductsFromNetwork()

    }

    override fun setupObserveData() {

    }


    //send
    private fun getProductsFromNetwork(){
        binding?.ivLogo?.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intentChannel.send(HomeIntent.GetProducts)
            }
        }
    }

    //render
    private fun renderState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is HomeStates.Idle -> {
                            Toast.makeText(requireContext(), "idle", Toast.LENGTH_LONG).show()
                        }
                        is HomeStates.Loading ->{
                            Log.i("displayList_tag","loading")
                        }

                        is HomeStates.GetProducts -> {
                            Toast.makeText(requireContext(),"${it.products.first().category}",Toast.LENGTH_SHORT).show()
                            Log.i("displayList_tag","${it.products.first().category}")

                        }
                        is HomeStates.Error ->{
                            Log.i("displayList_tag",it.message.toString())
                        }
                        is HomeStates.GetCategories->{
                            Log.i("displayList_tag","${it.categories}")
                        }

                    }
                }
            }
        }
    }





}