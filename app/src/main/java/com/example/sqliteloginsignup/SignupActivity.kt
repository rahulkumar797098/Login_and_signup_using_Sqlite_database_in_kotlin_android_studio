package com.example.sqliteloginsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqliteloginsignup.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper : DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gotoLogin.setOnClickListener {
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
        }

        databaseHelper = DatabaseHelper(this)
        binding.btnSignSA.setOnClickListener {
            val signupUsername = binding.edtUserNameSign.text.toString()
            val signupUserPassword = binding.userPasswordsignup.text.toString()
            signupDatabase(signupUsername,signupUserPassword)
        }
    }
    private fun signupDatabase(username : String , password : String) {
        val insertRowId = databaseHelper.insertUser(username , password)
        if (insertRowId != -1L) {
            Toast.makeText(this, "Signup SuccessFully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
        }else {
            Toast.makeText(this, "Signup Failed Try Again", Toast.LENGTH_SHORT).show()
        }
    }
}