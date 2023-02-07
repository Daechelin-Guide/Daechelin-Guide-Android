package com.example.guidedaechelin.main.fragment

import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseFragment
import com.example.guidedaechelin.databinding.FragmentMainBinding
import com.example.guidedaechelin.remote.RetrofitBuilder
import com.example.guidedaechelin.remote.data.MealResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main)  {

    override fun start() {

        getMeal()

        binding.txt.setOnClickListener {

            findNavController().navigate(R.id.action_mainFragment_to_mealFragment)

        }



    }


    fun getMeal(){

        val call = RetrofitBuilder.mealService.getMeal("2023","2","7")


        call.enqueue(object : Callback<MealResponseData> {

            override fun onResponse(call: Call<MealResponseData>, response: Response<MealResponseData>) {
                Log.d("성공", "onResponse: ${response.body()}")

                binding.txt.text = response.body()?.data?.dinner

            }

            override fun onFailure(call: Call<MealResponseData>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })


    }
}