package com.example.guidedaechelin.main.fragment

import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseFragment
import com.example.guidedaechelin.databinding.FragmentWriteBinding

class WriteFragment : BaseFragment<FragmentWriteBinding>(R.layout.fragment_write)  {

    override fun start() {

        binding.lengthTxt.text = binding.editTxt.text.toString().length.toString() + "/ 50"

        binding.completeBtn.setOnClickListener {
            Log.d("통신","${binding.starRating.rating}")
            findNavController().navigate(R.id.mealFragment)

        }

        binding.editTxt.addTextChangedListener{
            binding.lengthTxt.text = binding.editTxt.text.toString().length.toString() + "/ 50"
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}