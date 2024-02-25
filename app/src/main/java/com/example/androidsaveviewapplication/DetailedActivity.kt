package com.example.androidsaveviewapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class DetailedActivity : AppCompatActivity() {
    private lateinit var arrayList: ArrayList<Store>
    private lateinit var recyView: RecyclerView
    private lateinit var data:Store
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        arrayList = arrayListOf<Store>()
        recyView = findViewById(R.id.recyView)
        recyView.layoutManager = LinearLayoutManager(this)
        var bundle = intent.extras
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (bundle != null) {
                data = bundle.getSerializable("Data", Serializable::class.java) as Store
            }
            arrayList.add(data as Store)
            var customAdapter = CustomAdapter(arrayList)
            recyView.adapter = customAdapter
        }
        else {
            if (bundle != null) {
                @Suppress("DEPRECATION")
                data= bundle.getSerializable("Data") as Store
            }
            arrayList.add(data as Store)
            var customAdapter = CustomAdapter(arrayList)
            recyView.adapter = customAdapter
        }
        }
    }
