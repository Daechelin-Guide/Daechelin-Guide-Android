package com.example.guidedaechelin.meal.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseFragment
import com.example.guidedaechelin.base.BaseRecyclerviewAdapter
import com.example.guidedaechelin.databinding.FragmentMealBinding
import com.example.guidedaechelin.meal.data.CommentData
import com.example.guidedaechelin.meal.util.CommentAdapter

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal)  {

    private val commentAdapter : CommentAdapter by lazy { CommentAdapter() }

    private val commentDataSet = arrayListOf<CommentData>().apply {
        add(CommentData(1,"두카미가 최고다 ㅇㅈ?",3.5F))
        add(CommentData(2,"두카미가",3.5F))
        add(CommentData(3,"두카고다 ㅇㅈ?",3.5F))
        add(CommentData(4,"두카미 최고다 ㅇㅈ?",3.5F))
        add(CommentData(5,"미가 최고다 ㅇ",3.5F))
        add(CommentData(6,"두카미가 최고다",3.5F))
        add(CommentData(7,"두카미가고다 ㅇㅈ?",3.5F))
        add(CommentData(8,"카두미가 최고다",3.5F))
        add(CommentData(2,"두카미가",3.5F))
        add(CommentData(3,"두카고다 ㅇㅈ?",3.5F))
        add(CommentData(4,"두카미 최고다 ㅇㅈ?",3.5F))
        add(CommentData(5,"미가 최고다 ㅇ",3.5F))
        add(CommentData(6,"두카미가 최고다",3.5F))
        add(CommentData(7,"두카미가고다 ㅇㅈ?",3.5F))
        add(CommentData(8,"카두미가 최고다",3.5F))

    }


    override fun start() {

        binding.writeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mealFragment_to_writeFragment)

        }

        val mealType : String = "조식"
        val rating : Float = 2.5F

        binding.starRating.rating = rating

        when (mealType) {

            "조식" -> binding.mealKind.background = resources.getDrawable(R.drawable.rectangle1)

            "중식" -> binding.mealKind.background = resources.getDrawable(R.drawable.rectangle2)

            "석식" -> binding.mealKind.background = resources.getDrawable(R.drawable.rectangle3)

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
}