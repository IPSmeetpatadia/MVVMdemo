package com.example.mvvmdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.adapter.NoteAdapter
import com.example.mvvmdemo.dataclass.Note
import com.example.mvvmdemo.viewmodel.NoteViewModel

class NoteActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerView_note)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        findViewById<Button>(R.id.btn_note).setOnClickListener {
            addNote()
        }

        viewModel.list.observe(this) {
            recyclerView.adapter = NoteAdapter(this, it, viewModel)
        }
    }

    private fun addNote() {
        val edittext = findViewById<EditText>(R.id.edtxt_note)

        var note = Note(edittext.text.toString())
        viewModel.add(note)
        edittext.text.clear()
        recyclerView.adapter?.notifyDataSetChanged()
    }

}