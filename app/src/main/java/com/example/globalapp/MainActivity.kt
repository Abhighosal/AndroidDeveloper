package com.example.globalapp

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var edtName:EditText
    private lateinit var edtMobileNumber:EditText
    private lateinit var btnSave:Button
    private lateinit var btnView:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtName = findViewById(R.id.edtName)
        edtMobileNumber = findViewById(R.id.edtMobileNumber)
        btnSave = findViewById(R.id.btnSave)
        btnView = findViewById(R.id.btnView)

        btnSave.setOnClickListener {

            var name = edtName.text.toString()
            var mobileNumber = edtMobileNumber.text.toString()

            if(name.isEmpty() || mobileNumber.isEmpty())
            {
                Toast.makeText(this,"Empty field!!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(GlobalList.list.any { it.name == name })
            {
                Toast.makeText(this,"Already exists!!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
            {
                var t = DataUsers(name,mobileNumber)
                GlobalList.list.add(t)
                Toast.makeText(this,"Added Successfully!!",Toast.LENGTH_SHORT).show()
            }
        }

        btnView.setOnClickListener {
            var intent = Intent(this,DetailedActivity::class.java)
            startActivity(intent)
        }
    }
}