package com.example.guidedaechelin.remote

import com.example.guidedaechelin.remote.data.MealResponseData
import retrofit2.Call
import retrofit2.http.*

interface MealService {

    @GET("/menu")
    fun getMeal(

        @Query("year") year : String = "",
        @Query("month") month : String = "",
        @Query("day") day : String = ""

    ): Call<MealResponseData>

}
