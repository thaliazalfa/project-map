package com.example.projectmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarrecyclerview.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById <RecyclerView>(R.id.hype_recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ParentItem>()

        data.add(ParentItem(R.drawable.mediheal, "MEDIHEAL", "Sheetmask", R.drawable.star_pink, 4, 123))
        data.add(ParentItem(R.drawable.apieu, "APIE'U", "Water Light Tint", R.drawable.star_pink, 5, 123))
        data.add(ParentItem(R.drawable.skintific, "SKINTIFIC", "5X CERAMIDE BARRIER REPAIR", R.drawable.star_pink, 5, 123))

        val adapter = HypeAdapter(data)
        recyclerView.adapter = adapter
        
    }
}