package com.example.mvvmdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.dataclass.Note

class NoteViewModel: ViewModel() {

    var list = MutableLiveData<ArrayList<Note>>()
    val newList = arrayListOf<Note>()

    fun add(note: Note) {
        newList.add(note)
        list.value = newList
    }

    fun remove(note: Note) {
        newList.remove(note)
        list.value = newList
    }

}