package com.example.mealz.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealz.Adapter.MenuItemsAdapter
import com.example.mealz.Adapter.MenuItemClickListener
import com.example.mealz.Entity.Menu
import com.example.mealz.R
import com.example.mealz.ViewModel.MenuModel

import com.example.mealz.databinding.FragmentRestaurantMenuBinding

class RestaurantMenuFragment : Fragment() , MenuItemClickListener {
    lateinit var binding: FragmentRestaurantMenuBinding
    private lateinit var menuModel: MenuModel
    private lateinit var adapter: MenuItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = MenuItemsAdapter(requireActivity(), this)
        binding.recyclerView.adapter = adapter

        // Initialize MenuModel
        menuModel = ViewModelProvider(this).get(MenuModel::class.java)

        // Retrieve the restaurant ID from arguments
        val restaurantId = arguments?.getInt("Res")
        //Log.d("HI", restaurantId.toString())

        // Load menus for the selected restaurant
        restaurantId?.let { menuModel.loadMenus(it) }

        // Add Observers
        menuModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.progressBar2.visibility = View.VISIBLE
            } else {
                binding.progressBar2.visibility = View.GONE
            }
        }

        menuModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }

        menuModel.menus.observe(viewLifecycleOwner) { menus ->
            adapter.setMenu(menus)
        }
    }

    override fun onCellClickListener(data: Menu) {
        val bundle = bundleOf("mId" to data.ID_Menu)
        this.findNavController().navigate(
            R.id.action_restaurantMenuFragment_to_menuItemDetailsFragment,
            bundle
        )
    }
}

