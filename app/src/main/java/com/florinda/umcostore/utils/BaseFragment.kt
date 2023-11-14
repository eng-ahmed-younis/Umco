package com.florinda.umcostore.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> constructor(
    private val bindingInflater : (layoutInflater: LayoutInflater) -> VB
) : Fragment() {

    //bindings
    private var _binding:VB? = null
    //Binding
    protected val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding?.root
    }

    abstract fun setupViews()
    abstract fun setupObserveData()


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}