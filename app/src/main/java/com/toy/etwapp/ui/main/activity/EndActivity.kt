package com.toy.etwapp.ui.main.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.toy.etwapp.MainActivity
import com.toy.etwapp.R
import com.toy.etwapp.ui.main.fragment.ContentsFragment

class EndActivity : AppCompatActivity() {

    lateinit var homeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)



        homeBtn = findViewById(R.id.home_button)
        homeBtn.setOnClickListener(mClickLister)
    }

    val mClickLister : View.OnClickListener = object : View.OnClickListener{
        override fun onClick(v: View?) {
            // Goto the home
            startActivity(Intent(this@EndActivity,MainActivity::class.java))
        }
    }


}