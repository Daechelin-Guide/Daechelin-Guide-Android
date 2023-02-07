package com.example.guidedaechelin.meal.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.guidedaechelin.R
import com.example.guidedaechelin.base.BaseRecyclerviewAdapter
import com.example.guidedaechelin.databinding.ItemCommentBinding
import com.example.guidedaechelin.meal.data.CommentData

class CommentAdapter : BaseRecyclerviewAdapter<CommentData, ItemCommentBinding>(R.layout.item_comment){
    override fun action(data: CommentData, binding: ItemCommentBinding) {

        binding.commentTxt.text = data.comment

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

}

