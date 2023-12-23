package com.codingwithsandeep.e_commerceapp.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.codingwithsandeep.e_commerceapp.R
import com.codingwithsandeep.e_commerceapp.model.ProductListItem

class SeeAllProductsAdapter(
    private val productList: List<ProductListItem>
): RecyclerView.Adapter<SeeAllProductsAdapter.SeeAllProductsViewHolder>() {

    private lateinit var context: Context


    inner class SeeAllProductsViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        val tvProductTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        val ivProductImage: ImageView = itemView.findViewById(R.id.iv_ProductImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllProductsViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_product_item, parent, false)
        return SeeAllProductsViewHolder(view)

    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: SeeAllProductsViewHolder, position: Int) {
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

    }
}