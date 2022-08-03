package com.toy.etwapp.ui.main.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.green
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.toy.etwapp.R
import com.toy.etwapp.databinding.FragmentContentsBinding
import com.toy.etwapp.dto.Response
import com.toy.etwapp.ui.main.activity.ContentsActivity
import com.toy.etwapp.ui.main.viewmodel.ContentsViewModel

class ContentsFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = ContentsFragment()
    }

    private lateinit var viewModel: ContentsViewModel
    private lateinit var vv: View


    private var idx: Int = 0

    private var _binding: FragmentContentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var img1: ImageView
    private lateinit var img2: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContentsBinding.inflate(inflater, container, false)
        val v = inflater.inflate(R.layout.fragment_contents, container, false)
        vv = v
        img1 = v.findViewById(R.id.contents_img1)
        img2 = v.findViewById(R.id.contents_img2)

        viewModel = ViewModelProvider(this).get(ContentsViewModel::class.java)

        viewModel.foodIdx.observe(this.viewLifecycleOwner, Observer {

            Glide.with(v)
                .load("http://192.168.0.11:8080" + viewModel.getFoodData(it).imgPath)
                .into(img1)

            Glide.with(v)
                .load("http://192.168.0.11:8080" + viewModel.getFoodData(it + 1).imgPath)
                .into(img2)

        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel.api()

        //setView()
    }

    override fun onClick(v: View?) {
        when (vv.id) {
            R.id.contents_img1 -> {
                viewModel.imgClick(idx)
            }
            R.id.contents_img2 -> {
                viewModel.imgClick(idx + 1)
            }
        }
    }

    private fun setView(){

        Glide.with(vv)
            .load("http://192.168.0.11:8080" + viewModel.getFoodData(idx).imgPath)
            .into(img1)

        Glide.with(vv)
            .load("http://192.168.0.11:8080" + viewModel.getFoodData(idx + 1).imgPath)
            .into(img2)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}