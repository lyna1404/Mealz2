package com.example.mealz

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.example.mealz.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {// Inflate the layout
        binding = FragmentProfileBinding.inflate(layoutInflater)// for this fragment

        // Retrieve the value of the isLoggedIn key from SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        // Set the visibility of the logout button based on the value of isLoggedIn
        val logoutButton = binding.buttonLogout
        logoutButton.visibility = if (isLoggedIn) View.VISIBLE else View.INVISIBLE
        logoutButton.setOnClickListener(){
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()
            this.findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
       return binding.root
    }
}