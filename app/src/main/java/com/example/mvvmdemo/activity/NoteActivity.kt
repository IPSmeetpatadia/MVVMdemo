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
import com.example.mvvmdemo.viewmodel.ViewModelFactory

class NoteActivity : AppCompatActivity() {

    private lateinit var viewModel: NoteViewModel
    private lateinit var edittext: EditText
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        edittext = findViewById(R.id.edtxt_note)

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
        val note = Note(edittext.text.toString())
        viewModel.add(note)
        edittext.text.clear()
        recyclerView.adapter?.notifyDataSetChanged()
    }

}