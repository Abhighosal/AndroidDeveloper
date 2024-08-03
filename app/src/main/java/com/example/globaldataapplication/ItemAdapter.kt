package com.example.globaldataapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private var arrList: ArrayList<StoreString>) : RecyclerView.Adapter<ItemAdapter.InnerHolder>() {
    class InnerHolder(s: View):RecyclerView.ViewHolder(s)
    {
        private var textName: TextView = s.findViewById(R.id.textName)
        private var textAddress:TextView = s.findViewById(R.id.textAddress)
        private var textPassword:TextView = s.findViewById(R.id.textPassword)
        fun bind(t:StoreString)
        {
            textName.text = t.name
            textPassword.text = t.password
            textAddress.text = t.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        var infObject = LayoutInflater.from(parent.context).inflate(R.layout.activity_view,parent,false)
        return InnerHolder(infObject)
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        var item = arrList[position]
        holder.bind(item)
    }

    fun updateItems(filteredList: List<StoreString>) {
         arrList = filteredList as ArrayList<StoreString>
         notifyDataSetChanged()
    }

    fun reset(arrayList: ArrayList<StoreString>) {
        arrList = arrayList
        notifyDataSetChanged()
    }

}
