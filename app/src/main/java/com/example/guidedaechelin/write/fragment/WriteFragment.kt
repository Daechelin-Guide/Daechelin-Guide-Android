package com.example.guidedaechelin.main.fragment

import android.content.Context
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseFragment
import com.example.guidedaechelin.databinding.FragmentWriteBinding
import com.example.guidedaechelin.meal.data.CommentData
import com.example.guidedaechelin.meal.fragment.MealFragmentArgs
import com.example.guidedaechelin.remote.RetrofitBuilder
import com.example.guidedaechelin.remote.data.MealResponseData
import com.example.guidedaechelin.remote.data.PostCommentData
import com.example.guidedaechelin.remote.data.PostRatingRequestData
import com.example.guidedaechelin.remote.data.PostRatingResponseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteFragment : BaseFragment<FragmentWriteBinding>(R.layout.fragment_write)  {

    override fun start() {

        val args: WriteFragmentArgs by navArgs()
        var menu = args.menu

        binding.lengthTxt.text = binding.editTxt.text.toString().length.toString() + "/ 50"

        binding.completeBtn.setOnClickListener {

            postRating(binding.starRating.rating.toDouble(),menu)
            postComment(binding.editTxt.text.toString(),menu)


            findNavController().popBackStack()

            Log.d("통신","${binding.starRating.rating}")

        }

        binding.editTxt.addTextChangedListener{
            binding.lengthTxt.text = binding.editTxt.text.toString().length.toString() + "/ 50"
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }


    fun postRating(rating : Double, menu : String){

        val call = RetrofitBuilder.ratingService.postRating(PostRatingRequestData(rating,menu))

        Log.d("요청", "${rating},${menu}")

        call.enqueue(object : Callback<PostRatingResponseData> {

            override fun onResponse(call: Call<PostRatingResponseData>, response: Response<PostRatingResponseData>) {
                Log.d("성공", "onResponse: ${response.body()}")

            }

            override fun onFailure(call: Call<PostRatingResponseData>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })
    }

    fun postComment (comment : String, menu : String){

        val call = RetrofitBuilder.commentService.postComment(PostCommentData(menu,comment))

        Log.d("요청", "${comment},${menu}")

        call.enqueue(object : Callback<CommentData> {

            override fun onResponse(call: Call<CommentData>, response: Response<CommentData>) {
                Log.d("성공", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<CommentData>, t: Throwable) {
                Log.d("실패", "onResponse: ${t.message}")

            }
        })


    }

}