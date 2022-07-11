package com.toy.etwapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl("127.0.0.1")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(RetrofitService::class.java)

    fun getInstance(): RetrofitService? {
        return api
    }
}