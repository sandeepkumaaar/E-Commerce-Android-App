package com.codingwithsandeep.e_commerceapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithsandeep.e_commerceapp.R
import com.codingwithsandeep.e_commerceapp.adapters.SeeAllProductsAdapter
import com.codingwithsandeep.e_commerceapp.adapters.TrendingProductsAdapter
import com.codingwithsandeep.e_commerceapp.databinding.FragmentSeeAllProductsBinding

class SeeAllProductsFragment : Fragment() {

    private lateinit var _binding: FragmentSeeAllProductsBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_see_all_products, container, false)

        _binding = FragmentSeeAllProductsBinding.inflate(inflater, container, false)

        getTrendingProductsList()

        return _binding.root
    }

    private fun getTrendingProductsList() {
        binding.pbSeaAll .visibility = View.VISIBLE

        //val adapter = SeeAllProductsAdapter()
//        binding.rvSeaAllProducts.adapter = adapter
//        binding.rvSeaAllProducts.layoutManager = GridLayoutManager(requireContext(), 2)
//        binding.rvSeaAllProducts.setHasFixedSize(true)
    }


}