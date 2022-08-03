package com.toy.etwapp.ui.main.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
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

    var retrofit = Retrofit.retrofit
    var service = retrofit.create(RetrofitService::class.java)
    lateinit var foodArr: List<Response.Food>

    private val _foodIdx: MutableLiveData<Int> = MutableLiveData()
    val foodIdx: LiveData<Int>
        get() = _foodIdx

    init {

        _foodIdx.value = 0

        service.getFood().enqueue(object : Callback<Response.Res> {
            override fun onResponse(
                call: Call<Response.Res>,
                response: retrofit2.Response<Response.Res>
            ) {
                Log.d("log", response.body().toString())
                Log.d("log", response.body()!!.data.size.toString())

                foodArr = response.body()!!.data

                if (foodArr.size < 1) {
                    Log.d("log", "fail")
                    Log.d("log", foodArr.size.toString())
                }
            }

            override fun onFailure(call: Call<Response.Res>, t: Throwable) {
                // 실패
                Log.d("log", t.message.toString())
                Log.d("log", "fail")
            }
        })
    }

    fun imgClick(idx: Int) {
        // 선택 api 발사
        ///
        // 발싸
        ///
        
        // 다음 Index 설정
        // 마지막 index 확인
        // Size - 1 = _foodIndex 이면 마지막 선택 단계
        if (_foodIdx.value == foodArr.size - 1) {
            // 다음 단계 확인
            // 결승전이면 최종 승리자 화면 이동
            // 결승전이 아니면 다음 단계 사이즈로 재시작
        }
        else{
            _foodIdx.value = _foodIdx.value?.plus(2)
        }
    }

    fun getFoodData(idx: Int): Response.Food {
        return foodArr[idx]
    }

}