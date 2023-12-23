package com.codingwithsandeep.e_commerceapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithsandeep.e_commerceapp.R
import com.codingwithsandeep.e_commerceapp.adapters.PopularProductsAdapter
import com.codingwithsandeep.e_commerceapp.adapters.TrendingProductsAdapter
import com.codingwithsandeep.e_commerceapp.base.BaseFragment
import com.codingwithsandeep.e_commerceapp.databinding.FragmentHomeBinding
import com.codingwithsandeep.e_commerceapp.model.ProductListItem
import com.codingwithsandeep.e_commerceapp.network.ProductService
import com.codingwithsandeep.e_commerceapp.repository.ProductRepository
import com.codingwithsandeep.e_commerceapp.viewModel.ProductViewModel
import com.codingwithsandeep.e_commerceapp.viewModel.ProductViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productViewModelFactory: ProductViewModelFactory
    lateinit var productRepository: ProductRepository
    private val productService= ProductService.getInstance()
    lateinit var trendingProductList: List<ProductListItem>

    /* override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(
             inflater, R.layout.fragment_home, container, false
         )

         productRepository = ProductRepository(productService)
         productViewModelFactory = ProductViewModelFactory(productRepository)
         productViewModel = ViewModelProvider(this, ProductViewModelFactory(productRepository))[ProductViewModel::class.java]

         binding.tvSeeAl.setOnClickListener {
             Toast.makeText(requireContext(), "See all", Toast.LENGTH_SHORT).show()
         }


         getTrendingProductsList()
         gePopularProductsList()

         return binding.root
     }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productRepository = ProductRepository(productService)
        productViewModelFactory = ProductViewModelFactory(productRepository)
        productViewModel = ViewModelProvider(this, ProductViewModelFactory(productRepository))[ProductViewModel::class.java]

        binding.tvSeeAl.setOnClickListener {
            Toast.makeText(requireContext(), "See all", Toast.LENGTH_SHORT).show()
        }


        getTrendingProductsList()
        gePopularProductsList()

    }

    private fun getTrendingProductsList() {
        binding.pbHome.visibility = View.VISIBLE

        productViewModel.getAllProducts()
        productViewModel.productList.observe(viewLifecycleOwner){ productList->
            if (productList != null) {
                binding.pbHome.visibility = View.GONE
                Log.d("getTrendingProducts_TAG", "getWallPapers: $productList")
                trendingProductList = productList
                val adapter = TrendingProductsAdapter(productList)

                binding.rvTrendingProductsList.adapter = adapter
                binding.rvTrendingProductsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvTrendingProductsList.setHasFixedSize(true)
            } else {
                binding.pbHome.visibility = View.GONE
                Log.d("getTrendingProducts_TAG", "Something went wrong")
            }
        }
    }

    private fun gePopularProductsList() {
        binding.pbHome.visibility = View.VISIBLE

        productViewModel.getAllProducts()
        productViewModel.productList.observe(viewLifecycleOwner){ productList->
            if (productList != null) {
                binding.pbHome.visibility = View.GONE
                Log.d("getTrendingProducts_TAG", "getWallPapers: $productList")
                trendingProductList = productList
                val adapter = PopularProductsAdapter(productList)

                binding.rvPopularProducts.adapter = adapter
                binding.rvPopularProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvPopularProducts.setHasFixedSize(true)
            } else {
                binding.pbHome.visibility = View.GONE
                Log.d("getTrendingProducts_TAG", "Something went wrong")
            }
        }
    }
}