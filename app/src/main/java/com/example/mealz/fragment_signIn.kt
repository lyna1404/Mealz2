package com.example.mealz

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mealz.databinding.FragmentHomeBinding
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
            val userBDD = appDataBase.buildDatabase(requireContext())?.getUserEItemDAO()
                ?.getUserByemail(email)
            if (email == userBDD?.email && password == userBDD?.password) {
                val idMenu = arguments?.getInt("mId")
                val user = idMenu?.let { UserCart(id_menu = it, id_user = userBDD.idUser) }
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