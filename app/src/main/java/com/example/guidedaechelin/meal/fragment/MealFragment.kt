package com.example.guidedaechelin.meal.fragment

import android.util.Log
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseFragment
import com.example.guidedaechelin.base.BaseRecyclerviewAdapter
import com.example.guidedaechelin.databinding.FragmentMealBinding
import com.example.guidedaechelin.meal.data.CommentData
import com.example.guidedaechelin.meal.util.CommentAdapter
import com.example.guidedaechelin.remote.RetrofitBuilder
import com.example.guidedaechelin.remote.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal)  {

    var action : NavDirections? = null

    private val commentAdapter : CommentAdapter by lazy { CommentAdapter() }

    private var commentDataSet = listOf<CommentData>()



    override fun start() {

        val args: MealFragmentArgs by navArgs()
        var mealTypeData = args.mealType

        binding.mealTxt.text = "\\n\\n급식 정보를 불러오는 중입니다...\\n\\n"

        binding.dateTxt.text = "${getDate(mealTypeData.date.slice(IntRange(5,6)))}월 ${getDate(mealTypeData.date.slice(IntRange(8,9)))}일 (${mealTypeData.week.substring(mealTypeData.week.length-1,mealTypeData.week.length)})"

        binding.writeBtn.isEnabled = false

        binding.writeBtn.setOnClickListener {

            findNavController().navigate(action!!)

        }

        val mealType = mealTypeData.mealType
        val rating : Float = 2.5F


        binding.starRating.rating = rating

        when (mealType) {

            "조식" -> {
                getBreak(mealTypeData.date)
                binding.mealKind.background = resources.getDrawable(R.drawable.rectangle1)
            }

            "중식" -> {
                getLunch(mealTypeData.date)
                binding.mealKind.background = resources.getDrawable(R.drawable.rectangle2)
            }

            "석식" -> {
                getDinner(mealTypeData.date)
                binding.mealKind.background = resources.getDrawable(R.drawable.rectangle3)
            }

        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.mealKind.text = mealType

        binding.commentRecyclerview.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = commentAdapter
        }
        commentAdapter.submitList(commentDataSet)

        commentAdapter.setItemClickListener(object : BaseRecyclerviewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
            }
        })

    }

    fun getComment(menu: String){

        val call = RetrofitBuilder.commentService.getComment(menu)

        call!!.enqueue(object : Callback<List<CommentData>> {

            override fun onResponse(call: Call<List<CommentData>>, response: Response<List<CommentData>>) {
                Log.d("성공", "onResponse: ${response.body()}")

                if (response.body() != null){
                    commentDataSet = response.body()!!
                    commentAdapter.submitList(commentDataSet)
                }

            }

            override fun onFailure(call: Call<List<CommentData>>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })

    }

    fun getBreak(date : String){

        val call = RetrofitBuilder.mealService.getBreak(date)

        call!!.enqueue(object : Callback<MealBreakResponseData> {

            override fun onResponse(call: Call<MealBreakResponseData>, response: Response<MealBreakResponseData>) {
                Log.d("성공", "onResponse: ${response.body()}")

                binding.mealTxt.text=response.body()?.breakfast.toString().replace(", ","\n")
                action = MealFragmentDirections.actionMealFragmentToWriteFragment(response.body()?.breakfast.toString())
                getRating(response.body()?.breakfast.toString())
                getComment(response.body()?.breakfast.toString())
                binding.writeBtn.isEnabled = true

            }

            override fun onFailure(call: Call<MealBreakResponseData>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })

    }

    fun getLunch(date : String){

        val call = RetrofitBuilder.mealService.getLunch(date)

        call!!.enqueue(object : Callback<MealLunchResponseData> {

            override fun onResponse(call: Call<MealLunchResponseData>, response: Response<MealLunchResponseData>) {
                Log.d("성공", "onResponse: ${response.body()}")

                binding.mealTxt.text=response.body()?.lunch.toString().replace(", ","\n")
                action = MealFragmentDirections.actionMealFragmentToWriteFragment(response.body()?.lunch.toString())
                getRating(response.body()?.lunch.toString())
                getComment(response.body()?.lunch.toString())
                binding.writeBtn.isEnabled = true
            }

            override fun onFailure(call: Call<MealLunchResponseData>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })

    }

    fun getDinner(date : String){

        val call = RetrofitBuilder.mealService.getDinner(date)

        call!!.enqueue(object : Callback<MealDinnerResponseData> {

            override fun onResponse(call: Call<MealDinnerResponseData>, response: Response<MealDinnerResponseData>) {
                Log.d("성공", "onResponse: ${response.body()}")

                binding.mealTxt.text=response.body()?.dinner.toString().replace(", ","\n")
                action = MealFragmentDirections.actionMealFragmentToWriteFragment(response.body()?.dinner.toString())
                getRating(response.body()?.dinner.toString())
                getComment(response.body()?.dinner.toString())
                binding.writeBtn.isEnabled = true

            }

            override fun onFailure(call: Call<MealDinnerResponseData>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })

    }

    fun getRating(menu : String){

        val call = RetrofitBuilder.ratingService.getRating(menu)

        call!!.enqueue(object : Callback<RatingResponseData> {

            override fun onResponse(call: Call<RatingResponseData>, response: Response<RatingResponseData>) {
                Log.d("성공", "onResponse: ${response.body()}")

                if(response.body()?.star != null){ binding.starRating.rating = response.body()?.star!!.toFloat() }

            }

            override fun onFailure(call: Call<RatingResponseData>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })

    }

    fun getDate(date : String ) : String {

        if (date[0]=='0') return date.slice(IntRange(1,1))

        else return date
    }

}