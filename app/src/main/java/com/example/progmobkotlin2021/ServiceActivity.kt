package com.example.progmobkotlin2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ServiceActivity : AppCompatActivity() {
    lateinit var btnGetAPI : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        btnGetAPI = findViewById(R.id.btnGetAPI)

        btnGetAPI.setOnClickListener(View.OnClickListener { view ->
            var i = Intent(this@ServiceActivity,GetAPIActivity::class.java)
            startActivity(i)
        })
    }
}