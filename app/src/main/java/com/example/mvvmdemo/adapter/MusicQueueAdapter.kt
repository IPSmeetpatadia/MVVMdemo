package com.example.mvvmdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.dataclass.MusicDataClass
import kotlinx.android.synthetic.main.single_view_music.view.*

class MusicQueueAdapter(val context: Context, val musicQueueList:ArrayList<MusicDataClass>): RecyclerView.Adapter<MusicQueueAdapter.MusicViewHolder>() {

    class MusicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val musicName: TextView = itemView.queue_music_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_view_music, parent, false)
        return MusicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return musicQueueList.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.apply {
            musicName.text = musicQueueList[position].title
        }
    }
}