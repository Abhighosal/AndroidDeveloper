package com.example.firebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class CreateAccount : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var edtEmail:EditText
    private lateinit var edtPhone:EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        auth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.CreateEmail)
        edtPhone = findViewById(R.id.CreatePhoneNumber)
        edtPassword = findViewById(R.id.CreatePassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {

            val email = edtEmail.text.toString()
            val phone = edtPhone.text.toString()
            val password = edtPassword.text.toString()

            if(email.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty())
            {
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if(task.isSuccessful)
                    {
                        Toast.makeText(this@CreateAccount,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@CreateAccount,LoginActivity::class.java))
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this@CreateAccount,"Error: ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(this@CreateAccount,"Empty credentials",Toast.LENGTH_SHORT).show()
            }

        }

    }
}