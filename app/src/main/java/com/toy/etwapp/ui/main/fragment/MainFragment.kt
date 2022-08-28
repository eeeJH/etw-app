package com.toy.etwapp.ui.main.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBindings
import android.widget.Toast
import com.toy.etwapp.R
import com.toy.etwapp.databinding.FragmentMainBinding
import com.toy.etwapp.ui.main.activity.ContentsActivity
import com.toy.etwapp.ui.main.viewmodel.ContentsViewModel
import com.toy.etwapp.ui.main.viewmodel.MainViewModel

class MainFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        val btn16Contents: Button = v.findViewById(R.id.main_body_16contents)
        val btn32Contents: Button = v.findViewById(R.id.main_body_32contents)
        val btnClose: Button = v.findViewById(R.id.main_body_close)

        btn16Contents.setOnClickListener(this)
        btn32Contents.setOnClickListener(this)
        btnClose.setOnClickListener(this)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.main_body_16contents -> {
                Toast.makeText(context,"Go to 16", Toast.LENGTH_LONG).show()
                activity?.let{
                    //val intent = Intent(context, ContentsActivity::class.java)

                    val intent = Intent(context, ContentsActivity::class.java); //인텐트객체 선언
                    intent.apply {
                        this.putExtra("key",16); //값 전달
                    }
                    startActivity(intent); //액티비티 전환
                }
            }
            R.id.main_body_32contents -> {
                Toast.makeText(context,"Go to 32", Toast.LENGTH_LONG).show()
                activity?.let{
                    //val intent = Intent(context, ContentsActivity::class.java)

                    val intent = Intent(context, ContentsActivity::class.java); //인텐트객체 선언
                    intent.apply {
                        this.putExtra("key",32); //값 전달
                    }
                    startActivity(intent); //액티비티 전환
                }
            }
            R.id.main_body_close -> {
                // 종료
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}