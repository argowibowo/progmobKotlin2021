package com.example.progmobkotlin2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SampleList : AppCompatActivity() {
    lateinit var btnList : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_list)

        btnList = findViewById(R.id.btnShowList)
        btnList.setOnClickListener(View.OnClickListener { view ->
            var i = Intent(this@SampleList,SampleListView::class.java)
            startActivity(i)
        })
    }
}