package com.toy.etwapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.11:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}