package com.toy.etwapp.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toy.etwapp.Retrofit
import com.toy.etwapp.RetrofitService
import com.toy.etwapp.dto.Request
import com.toy.etwapp.dto.Response
import retrofit2.Call
import retrofit2.Callback
import java.net.URI

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
    private var arr = mutableListOf<Response.Food>()
    private var tempArr = mutableListOf<Response.Food>()
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
    var tonamentLen: Int = 0;
    lateinit var winner: Response.Food

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
                foodArr = response.body()!!.data
                tonamentLen = foodArr.size
                _foodIdx.value = 0

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

    // ImageView 클릭시
    // API
    fun imgClick(idx: Int) {
        Log.d("imgClick", "imgClick _foodIdx.value :  " + _foodIdx.value)


        // 다음 Index 설정
        // 마지막 index 확인
        // Size - 1 = _foodIndex 이면 마지막 선택 단계이므로 _foodArr 재설정
        // 16강이면 0 ~ 15
        // _foodIdx.value = 14일 때 아래 로직
        if (_foodIdx.value == tonamentLen - 2) {
            Log.d("imgClick", "1 _foodIdx.value " + _foodIdx.value)
            // 마지막 선택 호출
            setSelectedFood(idx)
            // 다음 단계 확인

            // 결승전이 아니면 다음 단계 사이즈로 재시작
            // 결승전이면 최종 승리자 화면 이동
            if(tonamentLen != 2) {
                Log.d("imgClick", "2 arr.size " + arr.size)

                // 선택받은 Food들 다음 Step을 위해 arr로 옮기기
                arr.clear()
                arr.addAll(tempArr)

                foodArr = tempArr.toList()

                Log.d("foodArr", foodArr.toString())
                tonamentLen /= 2

                tempArr.clear()
                // _foodArr 로 옮기기
                _foodArr.value = foodArr
                Log.d("imgClick", "2 _foodArr.value = arr :  arr.size " + arr.size)

                Log.d("arr", arr.toString())

                // 다음 선택 단계 : 초기화
                _foodIdx.value = 0
            }
            else {
                Log.d("imgClick", "결승전")

                winner = foodArr[idx]
                // 마지막 화면 호출 해라
                isDone = true
            }

        } else {
            Log.d("imgClick", "4")

            setSelectedFood(idx)
            _foodIdx.value = _foodIdx.value?.plus(2)
        }
    }

    fun setSelectedFood(idx: Int){
        Log.d("setSelectedFood", "setSelectedFood : " + foodArr[idx].name)
        tempArr.add(foodArr[idx])
        Log.d("setSelectedFood", "tempArr.size : " + tempArr.size)

        // 선택 api 발사
        // Food id 보내기
        service.sendId(foodArr[idx].userId).enqueue(object : Callback<Request.Req> {
            override fun onFailure(call: Call<Request.Req>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Request.Req>,
                response: retrofit2.Response<Request.Req>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("post", "data : " + data)
                }
            }
        })
    }

    fun getFoodData(idx: Int): Response.Food {
        /*
        if(arr.isEmpty()){
            Log.d("getFoodData", "foodArr !!!" + idx)
            return foodArr[idx]
        }
        else{
            Log.d("getFoodData", "arr !!!" + idx)
            //return arr[idx]
            return arr[idx]

        }
        */
        return foodArr[idx]
    }
}