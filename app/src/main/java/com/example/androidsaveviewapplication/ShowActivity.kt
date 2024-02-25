package com.example.androidsaveviewapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class ShowActivity : AppCompatActivity() {
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var phone: String
    private lateinit var address: String
    private lateinit var arrList: ArrayList<Store>
    private lateinit var recView: RecyclerView
    private lateinit var usAdapter: UserAdapter
    private lateinit var btnGoBack: Button

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        recView = findViewById(R.id.recView)
        arrList = arrayListOf<Store>()
        btnGoBack = findViewById(R.id.btnGoBack)

        recView.layoutManager = LinearLayoutManager(this)

        val bundle = intent.extras
        //Retrieving the arraylist based on SDK
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (bundle != null) {
                arrList = bundle.getSerializable("ArrayList", Serializable::class.java) as ArrayList<Store>

            }
        }
        else
        {
            if (bundle != null) {
                @Suppress("DEPRECATION")
                arrList = bundle.getSerializable("ArrayList") as ArrayList<Store>
            }
        }
        usAdapter = UserAdapter(arrList)
        recView.adapter = usAdapter

        // Size will return value starting from 1 but arraylist index start from 0
        usAdapter.notifyItemInserted(arrList.size - 1)

        btnGoBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("ArrList",arrList)
            startActivity(intent)
            finish()
        }
    }
}



