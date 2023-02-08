package com.example.guidedaechelin.remote

import com.example.guidedaechelin.remote.data.MealBreakResponseData
import com.example.guidedaechelin.remote.data.MealDinnerResponseData
import com.example.guidedaechelin.remote.data.MealLunchResponseData
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

    @GET("/lunch")
    fun getLunch(

        @Query("date") date : String = "",

    ): Call<MealLunchResponseData>

    @GET("/break")
    fun getBreak(

        @Query("date") date : String = "",

        ): Call<MealBreakResponseData>

    @GET("/dinner")
    fun getDinner(

        @Query("date") date : String = "",

        ): Call<MealDinnerResponseData>

}
