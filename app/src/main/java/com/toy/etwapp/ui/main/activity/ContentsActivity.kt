package com.toy.etwapp.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.toy.etwapp.R
import com.toy.etwapp.ui.main.fragment.ContentsFragment

class ContentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents)

        val contentCnt = intent.getIntExtra("key", 0)

        Log.d("ContentsActivity", "ContentsCnt : $contentCnt")

        val bundle = Bundle()
        bundle.putInt("key", contentCnt)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContentsFragment.newInstance())
                .commitNow()
        }
    }
}