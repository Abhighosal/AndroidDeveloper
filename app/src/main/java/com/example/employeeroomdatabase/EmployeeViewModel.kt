package com.example.employeeroomdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(private val repository: EmployeeRepository) : ViewModel() {

    val allEmployees: LiveData<List<Employee>> = repository.allEmployees

    fun insertEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(employee)
        }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch {
            repository.update(employee)
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(employee)
        }
    }

}
