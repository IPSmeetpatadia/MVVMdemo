package com.example.mvvmdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemo.R
import com.example.mvvmdemo.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            val name = findViewById<EditText>(R.id.edtxt_name)
            val pass = findViewById<EditText>(R.id.edtxt_pass)
            loginViewModel.login(name.text.toString(), pass.text.toString())
        }

        loginViewModel.loginResultLiveData.observe(this) { loginResult ->
            if (loginResult.success) {
                Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Fail!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}