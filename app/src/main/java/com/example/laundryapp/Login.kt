package com.example.laundryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)


        btnLoginListener()
        btnRegister()

    }
    private fun btnLoginListener(){
        val button = findViewById(R.id.btn_login) as Button
        button.setOnClickListener{
            startActivity(Intent(this, Login2::class.java))
        }
    }

    private fun btnRegister(){
        val button = findViewById(R.id.btn_register) as Button
        button.setOnClickListener{
            startActivity(Intent(this, Register::class.java))
        }
    }
}