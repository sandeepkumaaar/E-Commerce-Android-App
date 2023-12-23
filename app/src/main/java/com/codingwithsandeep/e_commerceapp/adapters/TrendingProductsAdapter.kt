package com.codingwithsandeep.e_commerceapp.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.codingwithsandeep.e_commerceapp.R
import com.codingwithsandeep.e_commerceapp.model.ProductListItem
import com.codingwithsandeep.e_commerceapp.view.fragments.HomeFragment

class TrendingProductsAdapter(
    private val productList: List<ProductListItem>
): RecyclerView.Adapter<TrendingProductsAdapter.TrendingProductsViewHolder>() {

    private lateinit var context: Context


    inner class TrendingProductsViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        val tvProductTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        val ivProductImage: ImageView = itemView.findViewById(R.id.iv_ProductImage)
        val selectProduct: ConstraintLayout = itemView.findViewById(R.id.cl_product)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingProductsViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_product_item, parent, false)
        return TrendingProductsViewHolder(view)

    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: TrendingProductsViewHolder, position: Int) {
        holder.tvProductTitle.text = productList[position].title
        holder.tvProductPrice.text = "${productList[position].price}"

        Glide.with(context)
            .load(productList[position].image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    //holder.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    //holder.progressBar.visibility = View.GONE
                    return false
                }
            }).into(holder.ivProductImage)

        holder.selectProduct.setOnClickListener {

            val productBundle = Bundle()
            productBundle.putString("productTitle", productList[position].title)
            productBundle.putString("productPrice", productList[position].price.toString())
            productBundle.putString("productDescription", productList[position].description)
            productBundle.putString("productRating", productList[position].rating.rate.toString())
            productBundle.putString("productImageUrl", productList[position].image)
            it.findNavController().navigate(R.id.productDetailsFragment, productBundle)

        }

    }
}