package com.codingwithsandeep.e_commerceapp.view.fragments.productDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingwithsandeep.e_commerceapp.R
import com.codingwithsandeep.e_commerceapp.databinding.FragmentDescriptionBinding


class DescriptionFragment : Fragment() {

    private lateinit var _binding: FragmentDescriptionBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)

        return _binding.root
    }

}