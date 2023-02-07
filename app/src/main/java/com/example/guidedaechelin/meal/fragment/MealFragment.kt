package com.example.guidedaechelin.meal.fragment

import androidx.navigation.fragment.findNavController
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseFragment
import com.example.guidedaechelin.databinding.FragmentMealBinding

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal)  {

    override fun start() {

        binding.writeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mealFragment_to_writeFragment)
        }
    }
}