package com.example.firebaseapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class CountryAdapter(
    private val countryList: MutableList<CountryData>
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryText: TextView = itemView.findViewById(R.id.txtCountry)
        val capitalText: TextView = itemView.findViewById(R.id.txtCapital)
        val tempText: TextView = itemView.findViewById(R.id.txtTemp)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countryData = countryList[position]
        holder.countryText.text = countryData.country
        holder.capitalText.text = countryData.capital
        holder.tempText.text = "${countryData.temp}°C"

        holder.btnDelete.setOnClickListener {
            val id = countryData.id
            if (!id.isNullOrEmpty()) {
                val database = FirebaseDatabase.getInstance()
                val reference = database.getReference("countries")

                reference.child(id).removeValue()
                    .addOnSuccessListener {
                        Log.d("Firebase", "Deleted country: $id")
                        // ✅ Do not manually remove item from list here
                        // Let Firebase's ValueEventListener handle UI refresh
                    }
                    .addOnFailureListener { exception ->
                        Log.e("Firebase", "Error deleting country", exception)
                    }
            }
        }
    }

    override fun getItemCount(): Int = countryList.size
}