package com.toy.etwapp

import retrofit2.Call
import com.google.gson.JsonObject
import com.toy.etwapp.dto.Response
import retrofit2.http.*

interface RetrofitService {
    //GET 예제
    @GET("posts/1")
    fun getUser(): Call<Response.Food>

    @GET("posts/{page}")
    fun getUserPage(@Path("page") page: String): Call<Response.Food>


//    @GET("posts/1")
//    fun getStudent(@Query("school_id") schoolId: Int,
//                   @Query("grade") grade: Int,
//                   @Query("classroom") classroom: Int): Call<ExampleResponse>
//
//
//    //POST 예제
//    @FormUrlEncoded
//    @POST("posts")
//    fun getContactsObject(@Field("idx") idx: String): Call<JsonObject>
}