package com.example.mvvmdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.dataclass.RetroDataClass
import com.example.mvvmdemo.interfaces.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitViewModel: ViewModel() {

    private var _mutableLiveData = MutableLiveData<List<RetroDataClass>>()
    val data: LiveData<List<RetroDataClass>> get() = _mutableLiveData

    fun loadData() {
        val urlBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(RetrofitInterface::class.java)

        urlBuilder.getData()
            .enqueue(object : Callback<List<RetroDataClass>?> {
                override fun onResponse(call: Call<List<RetroDataClass>?>, response: Response<List<RetroDataClass>?>) {
                    _mutableLiveData.value = response.body()
                }

                override fun onFailure(call: Call<List<RetroDataClass>?>, t: Throwable) {
                    Log.d("RetrofitViewModel", "RESPONSE FAIL!!")
                }
            })
    }

}