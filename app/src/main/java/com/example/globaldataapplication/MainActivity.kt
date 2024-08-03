package com.example.globaldataapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var edtUserName:EditText
    private lateinit var edtPassword:EditText
    private lateinit var edtAddress:EditText
    private lateinit var btnSave:MaterialButton
    private lateinit var btnView:MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtUserName = findViewById(R.id.edtUsername)
        edtAddress = findViewById(R.id.edtAddress)
        edtPassword = findViewById(R.id.edtPassword)
        btnSave = findViewById(R.id.btnSave)
        btnView = findViewById(R.id.btnView)

        btnSave.setOnClickListener {
            //user giving inputs and those are getting converted to string and stored in the given variable
            var name = edtUserName.text.toString()
            var address = edtAddress.text.toString()
            var password = edtPassword.text.toString()
            if(name.isEmpty() || address.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this,"Empty field!!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(GlobalData.arrList.any { it.name == name })
            {
                Toast.makeText(this,"Already exists!!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                //creating object
                var k = StoreString(name, password, address)
                //adding item one by one in arraylist
                GlobalData.arrList.add(k)
                Toast.makeText(this,"Successfully added!!",Toast.LENGTH_SHORT).show()
            }
        }

        btnView.setOnClickListener {
            //Moving to next activity
            var intent = Intent(this,SearchItemActivity::class.java)
            startActivity(intent)
        }
    }
}