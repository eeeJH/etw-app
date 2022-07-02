package com.toy.etwapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toy.etwapp.R
import com.toy.etwapp.ui.main.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}