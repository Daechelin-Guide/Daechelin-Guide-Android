package com.example.guidedaechelin.rank.fragment

import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseFragment
import com.example.guidedaechelin.base.BaseRecyclerviewAdapter
import com.example.guidedaechelin.databinding.FragmentMealBinding
import com.example.guidedaechelin.databinding.FragmentRankBinding
import com.example.guidedaechelin.main.data.MealTypeData
import com.example.guidedaechelin.meal.fragment.MealFragmentDirections
import com.example.guidedaechelin.meal.util.CommentAdapter
import com.example.guidedaechelin.rank.data.RankData
import com.example.guidedaechelin.rank.util.RankAdapter
import com.example.guidedaechelin.remote.RetrofitBuilder
import com.example.guidedaechelin.remote.data.RankResponseData
import com.example.guidedaechelin.remote.data.RatingResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankFragment : BaseFragment<FragmentRankBinding>(R.layout.fragment_rank)  {

    private var rankDataSet = listOf<RankResponseData>()

    private val rankAdapter : RankAdapter by lazy { RankAdapter() }

    override fun start() {

        getRank()



        binding.rankRecyclerview.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = rankAdapter
        }



        rankAdapter.setItemClickListener(object : BaseRecyclerviewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

            }
        })
    }

    fun getRank(){

        val call = RetrofitBuilder.ratingService.getRank()

        call!!.enqueue(object : Callback<List<RankResponseData>> {

            override fun onResponse(call: Call<List<RankResponseData>>, response: Response<List<RankResponseData>>) {
                Log.d("성공", "onResponse: ${response.body()}")

                if (response.body() != null) {
                    rankDataSet = response.body()!!
                    rankAdapter.submitList(rankDataSet)
                }
            }

            override fun onFailure(call: Call<List<RankResponseData>>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })


    }


}