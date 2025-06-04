package com.example.employeeroomdatabase

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(
    private var employees: List<Employee>,
    private val employeeViewModel: EmployeeViewModel
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtLocation: TextView = itemView.findViewById(R.id.txtLocation)
        val txtCompany: TextView = itemView.findViewById(R.id.txtCompany)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.txtName.text = employee.name
        holder.txtLocation.text = employee.location
        holder.txtCompany.text = employee.company

        // Clicking the item → move to Update screen
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CreateEmployeeActivity::class.java)
            intent.putExtra("id", employee.id)
            intent.putExtra("name", employee.name)
            intent.putExtra("location", employee.location)
            intent.putExtra("company", employee.company)
            context.startActivity(intent)
        }

        // Clicking delete button → delete the employee
        holder.btnDelete.setOnClickListener {
            employeeViewModel.deleteEmployee(employee)
        }
    }

    override fun getItemCount(): Int = employees.size

    fun setData(newList: List<Employee>) {
        employees = newList
        notifyDataSetChanged()
    }
}