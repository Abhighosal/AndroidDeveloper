package com.example.employeeroomdatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class CreateEmployeeActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtLocation: EditText
    private lateinit var edtCompany: EditText
    private lateinit var btnCreate: Button
    private lateinit var employeeViewModel: EmployeeViewModel
    private var employeeId: Int = 0
    private var originalName: String = ""
    private var originalLocation: String = ""
    private var originalCompany: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_employee)

        edtName = findViewById(R.id.edtName)
        edtLocation = findViewById(R.id.edtLocation)
        edtCompany = findViewById(R.id.edtCompany)
        btnCreate = findViewById(R.id.btnCreate)

        val dao = EmployeeDatabase.getDatabase(application).employeeDao()
        val repository = EmployeeRepository(dao)
        val factory = EmployeeViewModelFactory(repository)
        employeeViewModel = ViewModelProvider(this, factory)[EmployeeViewModel::class.java]

        employeeId = intent.getIntExtra("id", 0)
        originalName = intent.getStringExtra("name") ?: ""
        originalLocation = intent.getStringExtra("location") ?: ""
        originalCompany = intent.getStringExtra("company") ?: ""

        // If editing, set existing data
        if (employeeId != 0) {
            edtName.setText(originalName)
            edtLocation.setText(originalLocation)
            edtCompany.setText(originalCompany)
            btnCreate.text = "Update" // Optional: Change button text if editing
        } else {
            btnCreate.text = "Add"
        }

        // Button click
        btnCreate.setOnClickListener {
            saveEmployee()
        }
    }

    private fun saveEmployee() {
        val name = edtName.text.toString()
        val location = edtLocation.text.toString()
        val company = edtCompany.text.toString()

        if (name.isNotEmpty() && location.isNotEmpty() && company.isNotEmpty()) {
            if (employeeId == 0) {
                // Adding new employee
                val employee = Employee(name = name, location = location, company = company)
                employeeViewModel.insertEmployee(employee)
                Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show()
            } else {
                // Updating existing employee
                val employee = Employee(id = employeeId, name = name, location = location, company = company)
                employeeViewModel.updateEmployee(employee)
                Toast.makeText(this, "Employee Updated", Toast.LENGTH_SHORT).show()
            }

            // ✅ Go back to MainActivity explicitly
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
}

