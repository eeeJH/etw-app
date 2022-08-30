package com.toy.etwapp.ui.main.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.toy.etwapp.MainActivity
import com.toy.etwapp.R
import com.toy.etwapp.ui.main.fragment.ContentsFragment

class EndActivity : AppCompatActivity() {

    lateinit var homeBtn: Button
    lateinit var winner_name: TextView
    lateinit var winner_img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        winner_name = findViewById(R.id.winner_name)
        winner_img = findViewById(R.id.winner_img)

        val foodName = intent.getStringExtra("foodName")
        val foodImg = intent.getStringExtra("foodImg")

        Log.d("EndActivity", "onCreate : " + foodName + "   " + foodImg)

        Glide.with(this)
            .load("http://192.168.0.11:8080" + foodImg)
            .into(findViewById(R.id.winner_img))

        winner_name.text = foodName

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