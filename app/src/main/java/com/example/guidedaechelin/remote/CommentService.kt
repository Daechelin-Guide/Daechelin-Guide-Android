package com.example.guidedaechelin.remote

import com.example.guidedaechelin.meal.data.CommentData
import com.example.guidedaechelin.remote.data.PostCommentData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentService {


    @POST("/comment/regis")
    fun postComment(

        @Body post : PostCommentData

    ) : Call<CommentData>



    @GET("/comment/message")
    fun getComment(

        @Query ("menu") menu : String

    ) : Call<List<CommentData>>

}