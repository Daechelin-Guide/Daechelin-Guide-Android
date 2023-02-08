package com.example.guidedaechelin.main.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealTypeData(

    val mealType : String,
    val date : String,
    val week : String = ""

) : Parcelable
