package com.toy.etwapp.ui.main.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.toy.etwapp.R
import com.toy.etwapp.Retrofit
import com.toy.etwapp.RetrofitService
import com.toy.etwapp.dto.Response
import retrofit2.Call
import retrofit2.Callback

class ContentsViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var count = MutableLiveData<Int>()
    var retrofit = Retrofit.retrofit
    var service = retrofit.create(RetrofitService::class.java)
    lateinit var foodArr:List<Response.Food>

    init {
        count.value = 0

        service.getFood().enqueue(object : Callback<Response.Res> {
            override fun onResponse(call: Call<Response.Res>, response: retrofit2.Response<Response.Res>) {
                Log.d("log", response.body().toString())
                Log.d("log", response.body()!!.data.size.toString())

                foodArr = response.body()!!.data

                if(foodArr.size < 1){
                    Log.d("log","fail")
                    Log.d("log",foodArr.size.toString())
                }
            }

            override fun onFailure(call: Call<Response.Res>, t: Throwable) {
                // 실패
                Log.d("log",t.message.toString())
                Log.d("log","fail")
            }
        })
    }

    fun getFoodData(){

    }

}