package com.example.globalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DisplayAdapterService(private var list: ArrayList<DataUsers>) : RecyclerView.Adapter<DisplayAdapterService.InnerType>() {
    class InnerType(j: View):RecyclerView.ViewHolder(j)
    {
        private var txtName:TextView = j.findViewById(R.id.txtName)
        private var txtMobileNumber:TextView = j.findViewById(R.id.txtMobileNumber)

        fun hold(m:DataUsers)
        {
            txtName.text = m.name
            txtMobileNumber.text = m.mobileNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerType {
        var obInflater = LayoutInflater.from(parent.context).inflate(R.layout.design_template,parent,false)
        return InnerType(obInflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: InnerType, position: Int) {
        var item = list[position]
        holder.hold(item)
    }

}
