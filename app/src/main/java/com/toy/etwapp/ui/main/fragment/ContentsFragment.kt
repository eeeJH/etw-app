package com.toy.etwapp.ui.main.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toy.etwapp.R
import com.toy.etwapp.Retrofit
import com.toy.etwapp.RetrofitService
import com.toy.etwapp.ui.main.viewmodel.ContentsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentsFragment : Fragment() {

    companion object {
        fun newInstance() = ContentsFragment()
    }

    private lateinit var viewModel: ContentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contents, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContentsViewModel::class.java)
        // TODO: Use the ViewModel

        var retrofit = Retrofit.retrofit
        var service = retrofit.create(RetrofitService::class.java)

        service.getFood().enqueue(object : Callback<com.toy.etwapp.dto.Response.Food> {
                override fun onResponse(call: Call<com.toy.etwapp.dto.Response.Food>, response: Response<com.toy.etwapp.dto.Response.Food>) {
                    Log.d("log",response.toString())
                    Log.d("log", response.body().toString())
                }

                override fun onFailure(call: Call<com.toy.etwapp.dto.Response.Food>, t: Throwable) {
                    // 실패
                    Log.d("log",t.message.toString())
                    Log.d("log","fail")
                }
            })

    }

}