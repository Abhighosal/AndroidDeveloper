package com.example.androidarraylistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.properties.Delegates

class DisplayActivity : AppCompatActivity() {
    private lateinit var btnFrwrd:Button
    private lateinit var txtDisplay:TextView
    private lateinit var arrList:ArrayList<Storing>
    private var index by Delegates.notNull<Int>()
    private lateinit var btnBackward:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        btnFrwrd = findViewById(R.id.btnFrwrd)
        txtDisplay = findViewById(R.id.txtDisplay)
        btnBackward = findViewById(R.id.btnBackward)
        arrList = arrayListOf<Storing>()
        val bundle = intent.extras
        if(bundle!=null)
        {
            arrList = bundle.getSerializable("ArrayList") as ArrayList<Storing>
            index = bundle.getInt("index")
            txtDisplay.text = arrList[index].result
        }
        btnFrwrd.setOnClickListener {
            btnBackward.isEnabled = true
            index = (index+1)%arrList.size
            txtDisplay.text =arrList[index].result
            Log.d("tag","$index")
            Log.d("display", arrList.size.toString())
            if(index+1==arrList.size)
            {
                btnFrwrd.isEnabled = false
            }
            }
        btnBackward.setOnClickListener {
            btnFrwrd.isEnabled = true
            index = (index-1)%arrList.size
            txtDisplay.text =arrList[index].result
            if(index==0)
            {
                btnBackward.isEnabled = false
            }
        }
        }
    }


