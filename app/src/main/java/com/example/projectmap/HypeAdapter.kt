package com.example.projectmap

import com.example.belajarrecyclerview.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class HypeAdapter (private val hypeList : List<ParentItem>) : RecyclerView.Adapter<HypeAdapter.HypeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HypeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hype_product_item, parent, false)

        return HypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HypeViewHolder, position: Int) {
        val hype = hypeList[position]
        holder.mediheal.setImageResource(hype.logo)
        holder.brand.text = hype.brand
        holder.nama.text = hype.name
        holder.bintang.setImageResource(hype.bintang)
        holder.ratings.text = hype.rate_sum.toString()
        holder.jumlah.text = hype.total_product.toString()
    }

    override fun getItemCount(): Int {
        return hypeList.size
    }

    class HypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mediheal : ImageView
        var brand : TextView
        var nama : TextView
        var bintang : ImageView
        var ratings : TextView
        var jumlah : TextView

        init {
            mediheal = itemView.findViewById(R.id.gambar_product)
            brand = itemView.findViewById(R.id.brand_txt)
            nama = itemView.findViewById(R.id.name_txt)
            bintang = itemView.findViewById(R.id.star_homepage)
            ratings = itemView.findViewById(R.id.rate_txt)
            jumlah = itemView.findViewById(R.id.jumlah)
        }


    }
}