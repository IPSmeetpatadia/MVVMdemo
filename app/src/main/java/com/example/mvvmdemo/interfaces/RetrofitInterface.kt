package com.example.mvvmdemo.interfaces

import com.example.mvvmdemo.dataclass.RetroDataClass
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("posts")
    fun getData(): Call<List<RetroDataClass>>
}