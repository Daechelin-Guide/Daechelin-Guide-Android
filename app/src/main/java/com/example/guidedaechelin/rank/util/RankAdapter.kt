package com.example.guidedaechelin.rank.util

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseRecyclerviewAdapter
import com.example.guidedaechelin.databinding.ItemCommentBinding
import com.example.guidedaechelin.databinding.ItemRankBinding
import com.example.guidedaechelin.meal.data.AnimalData
import com.example.guidedaechelin.meal.data.CommentData
import com.example.guidedaechelin.meal.fragment.MealFragment
import com.example.guidedaechelin.rank.data.RankData
import com.example.guidedaechelin.remote.data.RankResponseData
import java.util.*

class RankAdapter : BaseRecyclerviewAdapter<RankResponseData, ItemRankBinding>(R.layout.item_rank){



    override fun action(data: RankResponseData, binding: ItemRankBinding) {


        binding.titleTxt.text = "${data.date}"
        binding.menuTxt.text = data.menu

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(ItemRankBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}

