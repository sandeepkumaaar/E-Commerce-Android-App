package com.codingwithsandeep.e_commerceapp.network

import com.codingwithsandeep.e_commerceapp.model.ProductList
import com.codingwithsandeep.e_commerceapp.model.ProductListItem
import com.codingwithsandeep.e_commerceapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductListItem>>


    companion object {
        var productService: ProductService? = null

        private val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        fun getInstance(): ProductService {
            if (productService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build()
                productService = retrofit.create(ProductService::class.java)
            }
            return productService!!
        }
    }
}
