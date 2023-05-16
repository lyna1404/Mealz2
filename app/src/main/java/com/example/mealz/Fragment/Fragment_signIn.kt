package com.example.mealz.Fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.mealz.Entity.UserCart
import com.example.mealz.appDataBase
import com.example.mealz.databinding.FragmentSignInBinding


class fragment_signIn : Fragment() {
    lateinit var binding: FragmentSignInBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()
            if (email == "userBDD?.email" && password == "userBDD?.password") {
                val idMenu = arguments?.getInt("mId")
                val user = idMenu?.let { UserCart(id_menu = it, id_user = 1) }
                user?.let {
                    appDataBase.buildDatabase(requireContext())?.getUserCartDAO()
                        ?.insertUserCart(it)
                }
                val sharedPreferences =
                    requireContext().getSharedPreferences("my_app", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

            } else {
                // Show error message
                Toast.makeText(
                    requireContext(),
                    "Invalid credentials, please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}