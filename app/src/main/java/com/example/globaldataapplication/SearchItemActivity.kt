package com.example.globaldataapplication

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchItemActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var recView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_item)
        searchView = findViewById(R.id.searchView)
        recView = findViewById(R.id.recView)

        recView.layoutManager = LinearLayoutManager(this)
        val t = ItemAdapter(GlobalData.arrList)
        recView.adapter = t

       searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
           override fun onQueryTextSubmit(p0: String?): Boolean{
               return false
           }

           override fun onQueryTextChange(p0: String?): Boolean {
               if (p0 != null) {
                   if (p0.isEmpty()) {
                       t.reset(GlobalData.arrList)
                   }
                   else {
                       val filteredList = GlobalData.arrList.filter { it.name == p0 }
                       t.updateItems(filteredList)
                   }
               }
               return true
           }
       })
    }
}