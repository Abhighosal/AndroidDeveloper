package com.example.androidsaveviewapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private var arrayList: ArrayList<Store>): RecyclerView.Adapter<CustomAdapter.InHolder>() {

    class InHolder(itemData: View):RecyclerView.ViewHolder(itemData)
    {
        var textName:TextView = itemData.findViewById(R.id.textName)
        var textEmail:TextView = itemData.findViewById(R.id.textEmail)
        var textPhone:TextView = itemData.findViewById(R.id.textPhone)
        var textAddress:TextView = itemData.findViewById(R.id.textAddress)

        fun embed(sr:Store)
        {
            textName.text = sr.name
            textEmail.text = sr.email
            textPhone.text = sr.phone
            textAddress.text = sr.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InHolder {
        var p = LayoutInflater.from(parent.context).inflate(R.layout.layoutcustom,parent,false)
        return InHolder(p)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: InHolder, position: Int) {
        var dataItem = arrayList[position]
        holder.embed(dataItem)
    }

}
