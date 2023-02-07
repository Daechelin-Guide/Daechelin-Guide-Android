package com.example.guidedaechelin.remote.data

data class MealResponseData(

    val data : Data

)

data class Data (

    val date : String,
    val breakfast : String,
    val dinner : String,
    val lunch : String

)