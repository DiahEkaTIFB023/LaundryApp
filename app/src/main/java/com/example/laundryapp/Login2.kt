package com.example.laundryapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.laundryapp.databinding.ActivityActivityLogin2Binding

class Login2 : AppCompatActivity() {
    private  lateinit var binding : ActivityActivityLogin2Binding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var edt_email: EditText
    private lateinit var edt_confirm_password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityActivityLogin2Binding = ActivityActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        edt_email =  binding.edtEmail
        edt_confirm_password = binding.edtConfirmPassword
        val btnLogin1: Button = binding.btnLogin1

        btnLogin1.setOnClickListener {
            val sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE)
            val emailSaved: String? = sharedPreferences.getString("email", "")
            val passwordSaved: String? = sharedPreferences.getString("password", "")

            //kondisi ketika memasukkan email dan password
            if(edt_email.text.toString() == emailSaved && edt_confirm_password.text.toString() == passwordSaved){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtRegisterLogin.setOnClickListener{
            startActivity(Intent(this, Register::class.java))
        }

    }

}