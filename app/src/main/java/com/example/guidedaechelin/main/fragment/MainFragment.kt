package com.example.guidedaechelin.main.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseFragment
import com.example.guidedaechelin.databinding.FragmentMainBinding
import com.example.guidedaechelin.main.data.MealTypeData
import com.example.guidedaechelin.main.util.CalendarDialog
import com.example.guidedaechelin.meal.fragment.MealFragmentDirections
import com.example.guidedaechelin.remote.RetrofitBuilder
import com.example.guidedaechelin.remote.data.MealResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main)  {

    var cal = Calendar.getInstance()
//    cal.time = Date()
    val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    var date = df.format(cal.time)
    var week : String = ""

    override fun start() {

        binding.imageView6.isEnabled = false
        binding.imageView7.isEnabled = false
        binding.imageView8.isEnabled = false

        binding.date.text = date.toString().replace("-","/")

        Log.d("날짜","${date}")
        Log.d("날짜","${date.slice(IntRange(0,3))}${date.slice(IntRange(5,6))}${date.slice(IntRange(8,9))}")

        getMeal()

        binding.rankBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_rankFragment)
        }

        binding.imageView6.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToMealFragment(MealTypeData("조식",date,week))
            findNavController().navigate(action)
        }
        binding.imageView7.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToMealFragment(MealTypeData("중식",date,week))
            findNavController().navigate(action)
        }
        binding.imageView8.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToMealFragment(MealTypeData("석식",date,week))
            findNavController().navigate(action)
        }

        binding.imageView3.setOnClickListener {
            cal.add(Calendar.DATE,1)
            date = df.format(cal.time)
            binding.date.text = date.toString().replace("-","/")
            getMeal()
        }

        binding.imageView2.setOnClickListener {
            cal.add(Calendar.DATE,-1)
            date = df.format(cal.time)
            binding.date.text = date.toString().replace("-","/")
            getMeal()
        }

        binding.date.setOnClickListener {

            val dialog = CalendarDialog(requireContext())
            dialog.showDialog()

            dialog.setOnClicklistener(object : CalendarDialog.OnDialogClickListener{
                override fun onClicked(dateCal: Calendar) {

                    cal = dateCal
                    date = df.format(cal.time)
                    binding.date.text = date.toString().replace("-","/")
                    getMeal()

                }
            })

        }

    }

    fun getMeal(){

        binding.imageView6.isEnabled = false
        binding.imageView7.isEnabled = false
        binding.imageView8.isEnabled = false

        binding.breakfastMenu.text = "급식 정보를 불러오는 중입니다..."
        binding.lunchMenu.text = "급식 정보를 불러오는 중입니다..."
        binding.dinnerMenu.text = "급식 정보를 불러오는 중입니다..."

        val call = RetrofitBuilder.mealService.getMeal(date.slice(IntRange(0,3)),date.slice(IntRange(5,6)),date.slice(IntRange(8,9)))

        call.enqueue(object : Callback<MealResponseData> {

            override fun onResponse(call: Call<MealResponseData>, response: Response<MealResponseData>) {
                Log.d("성공", "onResponse: ${response.body()}")

                week = response.body()!!.data.week

                if (response.body()?.data?.breakfast != null){
                    binding.imageView6.isEnabled = true
                    binding.breakfastMenu.text = response.body()?.data?.breakfast
                }
                else binding.breakfastMenu.text = "급식 정보가 없습니다."

                if (response.body()?.data?.lunch != null){
                    binding.imageView7.isEnabled = true
                    binding.lunchMenu.text = response.body()?.data?.lunch
                }
                else binding.lunchMenu.text = "급식 정보가 없습니다."

                if (response.body()?.data?.dinner != null){
                    binding.imageView8.isEnabled = true
                    binding.dinnerMenu.text = response.body()?.data?.dinner
                }
                else binding.dinnerMenu.text = "급식 정보가 없습니다."

            }

            override fun onFailure(call: Call<MealResponseData>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })

    }
}