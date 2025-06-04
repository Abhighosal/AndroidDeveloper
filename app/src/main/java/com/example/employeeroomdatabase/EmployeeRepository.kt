package com.example.employeeroomdatabase

class EmployeeRepository(private val dao: EmployeeDao) {

    val allEmployees = dao.getAllEmployees()

    suspend fun insert(employee: Employee) {
        dao.insertEmployee(employee)
    }

    suspend fun update(employee: Employee) {
        dao.update(employee)
    }

    suspend fun deleteEmployee(employee: Employee) {
        dao.deleteEmployee(employee)
    }
}
