package com.example.mvvmdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.adapter.RetrofitAdapter
import com.example.mvvmdemo.viewmodel.RetrofitViewModel

class RetrofitActivity : AppCompatActivity() {

    lateinit var viewModel: RetrofitViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        viewModel = ViewModelProvider(this)[RetrofitViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerView_retrofit)

        viewModel.data.observe(this) {
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = RetrofitAdapter(this,it)
        }
        viewModel.loadData()
    }

}