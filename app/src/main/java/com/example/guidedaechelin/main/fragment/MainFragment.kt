package com.example.guidedaechelin.main.fragment

import androidx.navigation.fragment.findNavController
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseFragment
import com.example.guidedaechelin.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main)  {

    override fun start() {
        findNavController().navigate(R.id.action_mainFragment_to_mealFragment)
    }
}