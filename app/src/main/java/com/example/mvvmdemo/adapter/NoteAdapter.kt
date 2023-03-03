package com.example.mvvmdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.dataclass.Note
import com.example.mvvmdemo.viewmodel.NoteViewModel

class NoteAdapter(val context: Context, val noteList: List<Note>, val viewModel: NoteViewModel): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.txt_item)
        val delete: ImageButton = itemView.findViewById(R.id.imgB_item)

        fun display(note: Note) {
            title.text = note.title
            delete.setOnClickListener {
                viewModel.remove(note)
                notifyItemRemoved(noteList.indexOf(note))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_view_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.display(noteList[position])
    }

}