package com.example.globalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailedActivity : AppCompatActivity() {
    private lateinit var recView:RecyclerView
    private lateinit var btnGoBack:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        recView = findViewById(R.id.recView)
        btnGoBack = findViewById(R.id.btnGoBack)
        recView.layoutManager = LinearLayoutManager(this)
        var g = DisplayAdapterService(GlobalList.list)
        recView.adapter = g

        btnGoBack.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}