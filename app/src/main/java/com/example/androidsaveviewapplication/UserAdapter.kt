package com.example.androidsaveviewapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private var arrList: ArrayList<Store>):RecyclerView.Adapter<UserAdapter.InnerHolder>() {
    private var onClickListener:OnClickListener? = null


    class InnerHolder(item: View) : RecyclerView.ViewHolder(item){
        var txtName: TextView = item.findViewById(R.id.txtName)
        var txtEmail: TextView = item.findViewById(R.id.txtEmail)


        fun binding(src: Store) {
            txtName.text = src.name
            txtEmail.text = src.email
        }

    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
            var obj = LayoutInflater.from(parent.context).inflate(R.layout.userlayout, parent, false)
            return InnerHolder(obj)
        }

        override fun getItemCount(): Int {
            return arrList.size
        }


        override fun onBindViewHolder(holder: InnerHolder, position: Int) {
            var data = arrList[position]
            var context = holder.itemView.context
            holder.binding(data)
            holder.itemView.setOnClickListener {
                if(onClickListener!=null) {
                    onClickListener!!.onClick(position, data)
                }
                    val intent = Intent(context, DetailedActivity::class.java)
                    intent.putExtra("Data", data)
                    context.startActivity(intent)
            }
        }

        interface OnClickListener {
            fun onClick(position:Int,sr:Store)
        }

       fun setOnClickListener(onClickListener: OnClickListener)
       {
           this.onClickListener = onClickListener
       }
    }





