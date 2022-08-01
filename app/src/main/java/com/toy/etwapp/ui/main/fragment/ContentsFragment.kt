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
import com.bumptech.glide.Glide
import com.toy.etwapp.R
import com.toy.etwapp.databinding.FragmentContentsBinding
import com.toy.etwapp.ui.main.activity.ContentsActivity
import com.toy.etwapp.ui.main.viewmodel.ContentsViewModel

class ContentsFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = ContentsFragment()
    }

    private lateinit var viewModel: ContentsViewModel
    private lateinit var vv: View
    private var _binding: FragmentContentsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContentsBinding.inflate(inflater, container, false)
        val v = inflater.inflate(R.layout.fragment_contents, container, false)
        var img1: ImageView = vv.findViewById(R.id.contents_img1)
        var img2: ImageView = vv.findViewById(R.id.contents_img2)

        vv = v

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContentsViewModel::class.java)
        // TODO: Use the ViewModel



        /*
        Glide.with(vv)
            .load("http://192.168.0.11:8080" + response.body()!!.data.get(0).imgPath.toString())
            .into(img1)

        Glide.with(vv)
            .load("http://192.168.0.11:8080" + response.body()!!.data.get(1).imgPath.toString())
            .into(img2)
        */
    }

    override fun onClick(v: View?) {
        when (vv.id) {
            R.id.contents_img1 -> {
                Toast.makeText(context,"Select !", Toast.LENGTH_LONG).show()

            }
            R.id.contents_img2 -> {

            }
        }
    }

    fun changeView(){
        
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}