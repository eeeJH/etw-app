package com.toy.etwapp

import retrofit2.Call
import com.google.gson.JsonObject
import com.toy.etwapp.dto.Request
import com.toy.etwapp.dto.Response
import retrofit2.http.*

interface RetrofitService {
    //GET 예제
    @GET("/api/food/16")
    fun getFood(): Call<Response.Res>

    @PUT("/api/food")
    fun sendId(@Body id : Long): Call<Request.Req>
}