package com.toy.etwapp

import retrofit2.Call
import com.google.gson.JsonObject
import com.toy.etwapp.dto.Response
import retrofit2.http.*

interface RetrofitService {
    //GET 예제
    @GET("/api/food")
    fun getFood(): Call<Response.Food>
}