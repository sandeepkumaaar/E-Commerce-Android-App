package com.codingwithsandeep.e_commerceapp.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.codingwithsandeep.e_commerceapp.R
import com.codingwithsandeep.e_commerceapp.adapters.VpDetailProductAdapter
import com.codingwithsandeep.e_commerceapp.base.BaseFragment
import com.codingwithsandeep.e_commerceapp.databinding.FragmentProductDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator


class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>(FragmentProductDetailsBinding::inflate) {

//    private lateinit var _binding: FragmentProductDetailsBinding
//    private val binding get() = _binding

    private var productTitle: String?= null
    private var productPrice: String?= null
    private var productDescription: String?= null
    private var productRating: String?= null
    private var productImageUrl: String?= null


    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        setProductData()
        addFragmentsToViewPager()

        return _binding.root
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //toolbarViewVisibility = View.GONE
        //toolbarMenusVisibility = false
        bottomNavigationViewVisibility = View.GONE

        setProductData()
        addFragmentsToViewPager()
    }

    private fun addFragmentsToViewPager(){

        val tabItems = arrayOf("Description", "Reviews", "Offers", "Policy")
        val fragmentAdapter = VpDetailProductAdapter(parentFragmentManager, lifecycle)
        binding.viewPager.adapter = fragmentAdapter

        TabLayoutMediator(binding.tabLayout,  binding.viewPager) { tab, position ->
            tab.text = tabItems[position]
        }.attach()
    }

    private fun setProductData(){
        val bundle = this.arguments
        if (bundle != null) {
            productTitle = bundle.getString("productTitle", "NA")
            productPrice = bundle.getString("productPrice", "NA")
            productDescription = bundle.getString("productDescription", "NA")
            productRating = bundle.getString("productRating", "NA")
            productImageUrl = bundle.getString("productImageUrl", "NA")
        }

        binding.tvDetailProductTitle.text = productTitle
        binding.tvDetailProductPrice.text = "$$productPrice"
        binding.tvDetailProductRating.text = productRating

        Glide.with(requireContext())
            .load(productImageUrl)
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
            }).into(binding.ivDetailProductImage)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val notification = menu?.findItem(R.id.action_notification)
        val addToCart = menu?.findItem(R.id.action_myCart)
        val favorite = menu?.findItem(R.id.action_favorite)
        favorite?.isVisible = true
        if (notification != null && addToCart != null){
            notification.isVisible = false
            addToCart.isVisible = false
        }
    }



}