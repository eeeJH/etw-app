package com.toy.etwapp.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.toy.etwapp.ContentsViewModelFactory
import com.toy.etwapp.R
import com.toy.etwapp.databinding.FragmentContentsBinding
import com.toy.etwapp.ui.main.activity.ContentsActivity
import com.toy.etwapp.ui.main.activity.EndActivity
import com.toy.etwapp.ui.main.viewmodel.ContentsViewModel
import java.time.LocalDate

class ContentsFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = ContentsFragment()
    }

    private lateinit var viewModel: ContentsViewModel
    private lateinit var viewModelFactory: ContentsViewModelFactory

    private lateinit var vv: View

    private var idx: Int = 0

    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img1_text: TextView
    private lateinit var img2_text: TextView
    private lateinit var tonanment: TextView

    private var contentCnt: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_contents, container, false)

        vv = v
        //contentCnt = arguments?.getInt("key", 0)!!

        img1 = v.findViewById(R.id.contents_img1)
        img2 = v.findViewById(R.id.contents_img2)

        img1.setOnClickListener(this)
        img2.setOnClickListener(this)

        img1_text = v.findViewById(R.id.contents_img1_text)
        img2_text = v.findViewById(R.id.contents_img2_text)
        tonanment = v.findViewById(R.id.tonanment)

        // ViewModel(몇강)
        viewModelFactory = ContentsViewModelFactory(contentCnt)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ContentsViewModel::class.java)

        viewModel.ffoodArr.observe(this.viewLifecycleOwner, Observer {

            Log.d("observe", "ffoodArr setting")
            if (it.size > 0) {


                Glide.with(vv.context)
                    .load("http://192.168.0.11:8080" + it[0].imgPath)
                    .into(v.findViewById(R.id.contents_img1))

                img1_text.text = it[0].name
                Log.d("ffoodArr img1_text", it[0].imgPath)
                Log.d("ffoodArr img1_text", it[0].name)


                Glide.with(v.context)
                    .load("http://192.168.0.11:8080" + it[1].imgPath)
                    .into(img2)

                img2_text.text = it[1].name
                Log.d("ffoodArr img2_text", it[1].imgPath)
                Log.d("ffoodArr img2_text", it[1].name)
            }
        })

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.foodIdx.observe(this.viewLifecycleOwner, Observer{

            tonanment.text = viewModel.tonamentLen.toString()

            Log.d("observe", "onActivityCreated setting")
            if (it >= 0 && it != viewModel.tonamentLen) {
                idx = it
                Log.d("observe", it.toString())

                Glide.with(vv.context)
                    .load("http://192.168.0.11:8080" + viewModel.getFoodData(it).imgPath)
                    .into(vv.findViewById(R.id.contents_img1))

                img1_text.text = viewModel.getFoodData(it).name

                Log.d("img1_text.text", viewModel.getFoodData(it).name)

                Glide.with(vv.context)
                    .load("http://192.168.0.11:8080" + viewModel.getFoodData(it + 1).imgPath)
                    .into(img2)

                img2_text.text = viewModel.getFoodData(it + 1).name
                Log.d("img2_text.text", viewModel.getFoodData(it + 1).name)
            }
        })

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.contents_img1 -> {
                viewModel.imgClick(idx)
            }
            R.id.contents_img2 -> {
                viewModel.imgClick(idx + 1)
            }
        }

        if(viewModel.isDone){
            Log.d("onClick", "viewModel.isDone")

            activity?.let{
                val intent = Intent(requireContext(), EndActivity::class.java)

                //val intent = Intent(activity, EndActivity::class.java); //인텐트객체 선언
                intent.apply {
                    this.putExtra("foodName",viewModel.winner.name); //값 전달
                    this.putExtra("foodImg",viewModel.winner.imgPath); //값 전달
                }

                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}