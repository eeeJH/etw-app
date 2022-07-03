package com.toy.etwapp.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toy.etwapp.R
import com.toy.etwapp.ui.main.fragment.ContentsFragment

class EndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContentsFragment.newInstance())
                .commitNow()
        }
    }
}