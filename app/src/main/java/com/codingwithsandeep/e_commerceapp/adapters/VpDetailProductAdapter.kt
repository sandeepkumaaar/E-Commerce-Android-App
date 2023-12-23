package com.codingwithsandeep.e_commerceapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codingwithsandeep.e_commerceapp.view.fragments.productDetail.DescriptionFragment
import com.codingwithsandeep.e_commerceapp.view.fragments.productDetail.OffersFragment
import com.codingwithsandeep.e_commerceapp.view.fragments.productDetail.PolicyFragment
import com.codingwithsandeep.e_commerceapp.view.fragments.productDetail.ReviewsFragment

class VpDetailProductAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> return DescriptionFragment()
            1-> return ReviewsFragment()
            2-> return OffersFragment()
            3-> return PolicyFragment()
        }
        return DescriptionFragment()
    }
}