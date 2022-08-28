package com.toy.etwapp.ui.main.viewmodel

import android.content.Context
import android.util.Log
import android.util.Size
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


/*
enum class ActionType{
    FIRST_IMGVIEW, SECOND_IMGVIEW
}
*/

// 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있다.
class ContentsViewModel(private val contentCnt: Int) : ViewModel() {

    // Retrofit 객체
    var retrofit = Retrofit.retrofit
    var service = retrofit.create(RetrofitService::class.java)

    // 뮤터블 라이브 데이터 - 수정 가능한 녀석
    // 라이브 데이터 - 값 변동 안됨
    // Food 데이터
    // Food 객체 초기화
    private val arr = mutableListOf<Response.Food>()
    // 서버에서 실제로 받는 Food 객체
    lateinit var foodArr: List<Response.Food>
    private val _foodArr = MutableLiveData<List<Response.Food>>()
    val ffoodArr: LiveData<List<Response.Food>>
        get() = _foodArr

    // Food Index
    private val _foodIdx: MutableLiveData<Int> = MutableLiveData()
    val foodIdx: LiveData<Int>
        get() = _foodIdx

    var isSuccess: Boolean = false
    var isDone: Boolean = false

    // 토너먼트 관련 객체들
    lateinit var isSelectFood: Array<Boolean?>
    var tonamentLen: Int = 0;

    // 뷰모델 초기화
    init {
        Log.d("", "ContentsViewModel - 생성자 호출")
        _foodArr.value = arr
        _foodIdx.value = -1
        request()
    }

    // 서버로 데이터 요청
    private fun request() {

        service.getFood().enqueue(object : Callback<Response.Res> {
            override fun onResponse(
                call: Call<Response.Res>,
                response: retrofit2.Response<Response.Res>
            ) {
                Log.d("onResponse", response.body().toString())
                Log.d("onResponse", response.body()!!.data.size.toString())

                foodArr = response.body()!!.data
                isSelectFood = Array(foodArr.size){false}
                tonamentLen = foodArr.size
                _foodIdx.value = 0

                Log.d("request", "11111111111111 " + foodArr[0].name)

                if (foodArr.isEmpty()) {
                    Log.d("onResponse Error", "fail")
                    isSuccess = false
                    return;
                }
                else
                {
                    _foodArr.value = foodArr
                    isSuccess = true
                    return;
                }
            }

            override fun onFailure(call: Call<Response.Res>, t: Throwable) {
                // 실패
                Log.d("onFailure", t.message.toString())
                Log.d("onFailure", "fail")
                isSuccess = false
            }
        })

    }

    fun imgClick(idx: Int) {
        Log.d("imgClick", "imgClickimgClick")
        // 선택 api 발사


        // 다음 Index 설정
        // 마지막 index 확인
        // Size - 1 = _foodIndex 이면 마지막 선택 단계이므로 _foodArr 재설정
        if (_foodIdx.value == tonamentLen - 1) {
            Log.d("imgClick", "1 _foodIdx.value " + _foodIdx.value)

            // 다음 단계 확인


            // 결승전이 아니면 다음 단계 사이즈로 재시작
            // 결승전이면 최종 승리자 화면 이동
            if(arr.size != 2) {
                Log.d("imgClick", "2 arr.size " + arr.size)


                _foodArr.value = arr
                // 다음 선택 단계 : 초기화
                isSelectFood = Array(foodArr.size){false}
                _foodIdx.value = 0
                tonamentLen /= 2
            }
            else {
                Log.d("imgClick", "3")

                // 메인 화면 호출 해라고 알려줌
                isDone = true
            }

        } else {
            Log.d("imgClick", "4")

            _foodIdx.value = _foodIdx.value?.plus(2)
        }
    }

    fun setSelectedFood(idx: Int){
        Log.d("setSelectedFood", "setSelectedFood : " + foodArr[idx].name)
        isSelectFood[idx] = true
        arr.add(foodArr[idx])
    }

    fun getFoodData(idx: Int): Response.Food {
        if(arr.isEmpty()){
            Log.d("getFoodData", "foodArr !!!")
            return foodArr[idx]
        }
        else{
            Log.d("getFoodData", "arr !!!")
            //return arr[idx]
            return foodArr[idx]

        }
    }

}