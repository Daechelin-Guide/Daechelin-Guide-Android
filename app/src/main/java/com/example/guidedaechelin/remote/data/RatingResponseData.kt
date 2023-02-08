package com.example.guidedaechelin.remote.data

data class RatingResponseData(

    val star : Double,
    val menu : String,
    val date : String

)

data class PostRatingResponseData(

    val star: Double,
    val menu: String,
    val date: String,
    val review : Int
)
