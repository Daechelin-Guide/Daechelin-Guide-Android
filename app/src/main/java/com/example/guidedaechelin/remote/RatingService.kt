package com.example.guidedaechelin.remote

import com.example.guidedaechelin.remote.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RatingService {


    @GET("/star")
    fun getRating(

        @Query("menu") menu : String

    ): Call<RatingResponseData>

    @POST("/regis")
    fun postRating(

        @Body post : PostRatingRequestData

    ) : Call<PostRatingResponseData>

    @GET("/rank")
    fun getRank(
    ) : Call<List<RankResponseData>>

}