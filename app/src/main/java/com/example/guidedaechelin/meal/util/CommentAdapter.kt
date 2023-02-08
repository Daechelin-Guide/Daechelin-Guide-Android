package com.example.guidedaechelin.meal.util

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
import com.example.guidedaechelin.meal.data.AnimalData
import com.example.guidedaechelin.meal.data.CommentData
import com.example.guidedaechelin.meal.fragment.MealFragment
import java.util.*

class CommentAdapter : BaseRecyclerviewAdapter<CommentData, ItemCommentBinding>(R.layout.item_comment){

    private val animalList = arrayListOf<AnimalData>().apply {
        add(AnimalData(R.drawable.bumblebee,"꿀벌"))
        add(AnimalData(R.drawable.cat,"고양이"))
        add(AnimalData(R.drawable.coala,"코알라"))
        add(AnimalData(R.drawable.chameleon,"카멜레온"))
        add(AnimalData(R.drawable.elephant,"코끼리"))
        add(AnimalData(R.drawable.rabbit,"토끼"))
        add(AnimalData(R.drawable.snake,"뱀"))
        add(AnimalData(R.drawable.tiger,"호랑이"))
        add(AnimalData(R.drawable.whale,"고래"))
        add(AnimalData(R.drawable.fox,"여우"))
        add(AnimalData(R.drawable.chicken,"닭"))
        add(AnimalData(R.drawable.cow,"황소"))
        add(AnimalData(R.drawable.crab,"꽃게"))
        add(AnimalData(R.drawable.dog,"강아지"))
        add(AnimalData(R.drawable.duck,"러버덕"))
        add(AnimalData(R.drawable.squid,"오징어"))
        add(AnimalData(R.drawable.eagle,"독수리"))
        add(AnimalData(R.drawable.jellyfish,"해파리"))
        add(AnimalData(R.drawable.lion,"사자"))
        add(AnimalData(R.drawable.pig,"돼지"))
        add(AnimalData(R.drawable.shark,"상어"))
        add(AnimalData(R.drawable.shrimp,"새우"))
        add(AnimalData(R.drawable.turtle,"거북이"))
        add(AnimalData(R.drawable.tyrannosaurus,"티라노사우르스"))
        add(AnimalData(R.drawable.stegosaurus,"스테고사우르스"))
    }

    private val adList = listOf<String>(
        "이쁜",
        "용감한",
        "길 잃은",
        "무서운",
        "거대한",
        "커다란",
        "조그만",
        "귀여운",
        "개발자",
        "AI",
        "로봇",
        "사나운",
        "잘생긴",
        "든든한",
        "소심한",
        "대담한",
        "별거 아닌",
        "지친",
        "자본주의",
        "그저 그런",
        "무거운",
        "가벼운",
        "어설픈",
        "자비로운",
        "너그러운",
        "상냥한",
        "친절한",
        "인싸",
        "아싸",
        "까다로운",
        "현명한",
        "멍청한",
        "편식쟁이",
        "미식가",
        "미슐랭",
        "순수한"

    )

    val random = Random()

    override fun action(data: CommentData, binding: ItemCommentBinding) {

        var num = random.nextInt(animalList.size)

        binding.peopleIcon.background = binding.root.resources.getDrawable(animalList[num].drawable)
        binding.writerTxt.text = animalList[num].animal

        num = random.nextInt(adList.size)

        binding.adTxt.text = adList[num]

        binding.commentTxt.text = data.message

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

}

