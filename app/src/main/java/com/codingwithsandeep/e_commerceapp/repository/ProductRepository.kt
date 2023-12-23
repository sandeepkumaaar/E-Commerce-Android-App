package com.codingwithsandeep.e_commerceapp.repository

import com.codingwithsandeep.e_commerceapp.network.ProductService

class ProductRepository(private val productService: ProductService) {


    suspend fun getAllProducts() = productService.getAllProducts()


}