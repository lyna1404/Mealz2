package com.example.mealz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mealz.databinding.ActivityHomePageBinding
import com.example.mealz.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val screen = intent.getStringExtra("screen")
        binding.buttonLogin.setOnClickListener {

            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()
            val userBDD = appDataBase.buildDatabase(this)?.getUserEItemDAO()?.getUserByemail(email)
            if (email == userBDD?.email && password == userBDD?.password) {
                val sharedPreferences = getSharedPreferences("my_app", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit{
                    putBoolean("isLoggedIn", true)
                }
                // Redirect to cart page
                val intent = Intent(this,HomePage::class.java)
                intent.putExtra("screen",screen)
                startActivity(intent)



            } else {
                // Show error message
                Toast.makeText(this, "Invalid credentials, please try again", Toast.LENGTH_SHORT).show()
            }
        }
    }
}