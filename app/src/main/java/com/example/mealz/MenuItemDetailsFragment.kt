package com.example.mealz
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.mealz.databinding.FragmentMenuItemDetailsBinding

class MenuItemDetailsFragment : Fragment(){
    lateinit var binding: FragmentMenuItemDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val menuid = arguments?.getInt("mId")

        var menuI:MenuItem? = menuid?.let {
            appDataBase.buildDatabase(requireContext())?.getMenuItemDAO()?.getItemById(
                it
            )
        }
        binding = FragmentMenuItemDetailsBinding.inflate(layoutInflater)
        binding.Category.text = menuI?.type
        binding.Name.text = menuI?.name
        HomePage().setGradientTextColor(binding.Price, Color.parseColor("#DC220F"), Color.parseColor("#F05600"))
        binding.Price.text = menuI?.price.toString()+" DA"
        binding.ServingInfo.text = menuI?.quantity.toString()+" Persons"
        binding.Description.text = menuI?.description
        // Add a button to redirect user only if they are logged in
        binding.cart.setOnClickListener {


                // Redirect to authenticated page
                this.findNavController().navigate(R.id.action_menuItemDetailsFragment_to_cartFragment)
                // Insert element to database

                var resto = appDataBase.buildDatabase(requireContext())?.getUserCartDAO()?.getItemsForUser(1)
                if (resto?.isEmpty() == true || resto?.get(0)?.id_res ==menuI?.id_res ) {
                    menuI?.idMenu?.let { it1 -> UserCart(id_menu = it1, id_user = 1) }?.let { it2 ->
                        appDataBase.buildDatabase(requireContext())?.getUserCartDAO()
                            ?.insertUserCart(
                                it2
                            )
                    }
                }else{
                    Toast.makeText(requireContext(),"You can only make commands from same restaurant",Toast.LENGTH_SHORT).show()
                }


        }

        return binding.root
    }


}