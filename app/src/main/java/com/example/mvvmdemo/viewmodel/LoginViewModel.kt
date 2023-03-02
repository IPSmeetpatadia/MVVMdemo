package com.example.mvvmdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.dataclass.User

class LoginViewModel: ViewModel() {

    private val loginLiveData = MutableLiveData<LoginResult>()
    val loginResultLiveData: LiveData<LoginResult> = loginLiveData

    private val user = User("TestUser", "test123")

    fun login(name: String, password:String) {
        val success = user.username == name && user.password == password
        loginLiveData.value = LoginResult(success)
    }

    data class LoginResult(
        val success: Boolean
    )
}