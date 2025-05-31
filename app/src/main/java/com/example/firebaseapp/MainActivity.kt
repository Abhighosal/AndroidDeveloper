package com.example.firebaseapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var edtCountry: EditText
    private lateinit var edtCapital: EditText
    private lateinit var edtWeather: EditText
    private lateinit var btnAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter
    private lateinit var btnLogout: Button
    private val countryList = ArrayList<CountryData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance().getReference("countries")

        edtCountry = findViewById(R.id.edtCountry)
        edtCapital = findViewById(R.id.edtCapital)
        edtWeather = findViewById(R.id.edtWeather)
        btnAdd = findViewById(R.id.btnAdd)
        recyclerView = findViewById(R.id.recyclerView)
        btnLogout = findViewById(R.id.btnLogout)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CountryAdapter(countryList)
        recyclerView.adapter = adapter

        btnAdd.setOnClickListener {
            val country = edtCountry.text.toString()
            val capital = edtCapital.text.toString()
            val temp = edtWeather.text.toString()

            if (country.isNotEmpty() && capital.isNotEmpty() && temp.isNotEmpty()) {
                val id = database.push().key!!
                val data = CountryData(id,country, capital, temp)
                database.child(id).setValue(data).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show()
                        edtCountry.text.clear()
                        edtCapital.text.clear()
                        edtWeather.text.clear()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        fetchData()

        btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                startActivity(Intent(this@MainActivity,LoginActivity::class.java))
                finish() // You can also use startActivity(Intent(this, LoginActivity::class.java)) if needed
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun fetchData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                countryList.clear()
                if (snapshot.exists()) {
                    for (item in snapshot.children) {
                        val data = item.getValue(CountryData::class.java)
                        if (data != null) {
                            countryList.add(data)
                        } else {
                            Log.w("Firebase", "Null item found in snapshot")
                        }
                    }
                    Log.d("Firebase", "Items loaded: ${countryList.size}")
                } else {
                    Log.d("Firebase", "No items in snapshot")
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}