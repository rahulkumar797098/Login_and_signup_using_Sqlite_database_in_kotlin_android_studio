package com.example.sqliteloginsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqliteloginsignup.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToSignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
            finish()
        }

        databaseHelper = DatabaseHelper(this)

        binding.btnLoginLA.setOnClickListener {
            val loginUsername = binding.edtUserNameLogin.text.toString()
            val loginPassword = binding.userPasswordLogin.text.toString()
            loginDatabase(loginUsername,loginPassword)
        }

    }

    private fun loginDatabase(username : String , password : String){
        val userExists = databaseHelper.readUser(username,password)
        if (userExists){
            Toast.makeText(this, "Login SuccessFully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }
        else if (! userExists){
            Toast.makeText(this, "Please Create Account First", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}

