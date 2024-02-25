package com.example.androidsaveviewapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.Serializable
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private lateinit var edtEmailAddress: EditText
    private lateinit var edtAddress: EditText
    private lateinit var edtName: EditText
    private lateinit var edtPhoneNumber: EditText
    private lateinit var btnSave: Button
    private lateinit var arrList: ArrayList<Store>
    private lateinit var btnView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrList = arrayListOf<Store>()
        edtEmailAddress = findViewById(R.id.edtEmailAddress)
        edtAddress = findViewById(R.id.edtAddress)
        edtName = findViewById(R.id.edtName)
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber)
        btnSave = findViewById(R.id.btnSave)
        btnView = findViewById(R.id.btnView)

        //This is the arraylist
        arrList = arrayListOf<Store>()

        // Global arraylist based on SDK
        // Build.VERSION.SDK_INT this will find what is the sdk of the phone where the app is getting install and then it will check whether it is above Tiramisu
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            var bundle = intent.extras
            if (bundle != null) {
                arrList = bundle.getSerializable("ArrList",Serializable::class.java) as ArrayList<Store>
            }
        } else{
            var bundle = intent.extras
            if (bundle != null) {
                @Suppress("DEPRECATION")
                arrList = bundle.getSerializable("ArrList") as ArrayList<Store>
            }
        }


        btnSave.setOnClickListener {
            // User input taken
            val name = edtName.text.toString()
            val email = edtEmailAddress.text.toString()
            val phone = edtPhoneNumber.text.toString()
            val address = edtAddress.text.toString()

            //Check whether its blank or not
            if (name.isBlank() || email.isBlank() || phone.isBlank() || address.isBlank()) {
                Toast.makeText(this, "Empty Credential", Toast.LENGTH_SHORT).show()
            } else if (name.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && address.isNotBlank()) {
                //This will work only if data exist in arraylist
                if (arrList.any{it.name == name} || arrList.any{it.email == email}) {
                    Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                    edtName.setText("")
                    edtEmailAddress.setText("")
                    edtPhoneNumber.setText("")
                    edtAddress.setText("")
                } // For 1st entry or any new entry
                else{
                    var f = Store(name, email, phone, address)      // Parameter Constructor
                    Toast.makeText(this,"Successfully saved",Toast.LENGTH_SHORT).show()
                    arrList.add(f)
                    Log.d("tag","$arrList")
                    edtName.setText("")
                    edtEmailAddress.setText("")
                    edtPhoneNumber.setText("")
                    edtAddress.setText("")
                }

            }
        }

        btnView.setOnClickListener {
            val intent = Intent(this, ShowActivity::class.java)
            intent.putExtra("ArrayList", arrList)
            startActivity(intent)
            finish()
        }
    }
}








