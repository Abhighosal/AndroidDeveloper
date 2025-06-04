package com.example.employeeroomdatabase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var addButton:FloatingActionButton
    private lateinit var recView:RecyclerView
    private lateinit var employeeViewModel:EmployeeViewModel
    private lateinit var t: EmployeeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addButton = findViewById(R.id.addButton)
        recView = findViewById(R.id.recView)
        recView.layoutManager = LinearLayoutManager(this)


        val dao = EmployeeDatabase.getDatabase(application).employeeDao()
        val repository = EmployeeRepository(dao)
        val factory = EmployeeViewModelFactory(repository)
        employeeViewModel = ViewModelProvider(this, factory)[EmployeeViewModel::class.java]
        t = EmployeeAdapter(emptyList(), employeeViewModel)
        recView.adapter = t

        employeeViewModel.allEmployees.observe(this) { employees ->
            t.setData(employees)
        }


        addButton.setOnClickListener {

            val intent = Intent(this@MainActivity,CreateEmployeeActivity::class.java)
            startActivity(intent)
        }


    }
}