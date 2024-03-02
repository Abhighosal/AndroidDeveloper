package com.example.androidarraylistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var edtFirstNumber:EditText
    private lateinit var edtSecondNumber:EditText
    private lateinit var btnSave:Button
    private lateinit var arrList:ArrayList<Storing>
    private lateinit var btnView:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtFirstNumber = findViewById(R.id.edtFirstNumber)
        edtSecondNumber = findViewById(R.id.edtSecondNumber)
        btnSave = findViewById(R.id.btnSave)
        btnView = findViewById(R.id.btnView)
        arrList = arrayListOf<Storing>()
        btnSave.setOnClickListener {
            val firstNum = edtFirstNumber.text.toString()
            val secondNum = edtSecondNumber.text.toString()
            if(firstNum.isBlank() || secondNum.isBlank())
            {
                Toast.makeText(this,"Empty field",Toast.LENGTH_SHORT).show()
            }
            else {
                val firstNumber = firstNum.toInt()
                val secondNumber = secondNum.toInt()
                val add = firstNumber + secondNumber
                val g = Storing(add.toString())
                arrList.add(g)
                Toast.makeText(this, "Successfully stored in arraylist", Toast.LENGTH_SHORT).show()
            }
        }
        btnView.setOnClickListener {

            val intent = Intent(this,DisplayActivity::class.java)
            intent.putExtra("ArrayList",arrList)
            intent.putExtra("index",0)
            startActivity(intent)
        }
    }
}