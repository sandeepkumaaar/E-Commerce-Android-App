package com.codingwithsandeep.e_commerceapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB: ViewBinding, VM: ViewModel>: AppCompatActivity() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }



    abstract fun setUpView(savedInstanceState: Bundle?)

    abstract fun inflateLayout(layoutInflater: LayoutInflater) : VB


    override fun onDestroy() {
        super.onDestroy()
    }

}