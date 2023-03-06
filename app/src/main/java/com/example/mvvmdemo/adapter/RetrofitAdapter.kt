package com.example.mvvmdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.dataclass.RetroDataClass

class RetrofitAdapter(private val context: Context, private val retrofitList: List<RetroDataClass>): RecyclerView.Adapter<RetrofitAdapter.RetroViewHolder>() {

    class RetroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.txt_retroItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetroViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_view_retrofit, parent, false)
        return RetroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return retrofitList.size
    }

    override fun onBindViewHolder(holder: RetroViewHolder, position: Int) {
        holder.apply {
            id.text = retrofitList[position].id.toString()
        }
    }

}